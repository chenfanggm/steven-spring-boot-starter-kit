package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Appointment;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.jdbi.dao.AppointmentDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final ProfileService profileService;

    @Autowired
    public AppointmentService(final AppointmentDao appointmentDao,
                              final ProfileService profileService) {
        this.appointmentDao = appointmentDao;
        this.profileService = profileService;
    }


    public Appointment getAppointmentById(final Long appointmentId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final Appointment appointment = appointmentDao.getById(appointmentId);
            if (appointment == null) {
                throw new ItemNotFoundException();
            }
            return appointment;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public List<Appointment> getAppointmentByUserId(final Long userId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final List<Appointment> appointmentList = appointmentDao.getAppointmentByUserId(userId);
            if (appointmentList == null || appointmentList.isEmpty()) {
                throw new ItemNotFoundException();
            }
            return appointmentList;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public Appointment createAppointment(final Appointment appointment)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final Long appointmentId = appointmentDao.create(appointment.getUserId(), appointment.getAppointmentType(),
                appointment.getTopicId(), appointment.getSummary(), appointment.getQuestion(), appointment.getMeetupTime(),
                appointment.getMeetupAddress(),Constant.APPOINTMENT_STATUS_ACTIVE, timeNow);

            // update profile
            final Profile profile = Profile.builder()
                .userId(appointment.getUserId())
                .contactWechat(appointment.getContactWechat())
                .build();

            profileService.updateProfile(profile);

            return getAppointmentById(appointmentId);

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }
}
