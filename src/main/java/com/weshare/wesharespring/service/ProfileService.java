package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.BadRequestException;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.jdbi.dao.ProfileDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileService(final ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile getProfileByUserId(final Long userId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final Profile profile = profileDao.getByUserId(userId);
            if (profile == null) {
                throw new ItemNotFoundException();
            }
            return profile;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public Profile upsertProfile(final Profile profile)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            profileDao.create(profile.getUserId(), profile.getFirstName(), profile.getLastName(), profile.getGender(),
                profile.getMajor(), profile.getSummary(), profile.getContactWechat(), profile.getContactEmail(),
                profile.getContactPhone(), profile.getWorkCity(), profile.getWorkState(), profile.getWorkAddress(),
                profile.getWorkCompany(), profile.getWorkPosition(), profile.getWorkYear(), profile.getWorkReferStatus(),
                profile.getWorkReferPosition(), profile.getAvailableTime(), profile.getPreferTime(), profile.getPreferAddress(),
                profile.getPreferPayment(), profile.getPhoneRate(), profile.getMeetupRate(), profile.getMeetupBonus(),
                profile.getHeadShotUrl(), profile.getTimeZone(), Constant.PROFILE_STATUS_INCOMPLETE, timeNow);

            return getProfileByUserId(profile.getUserId());

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                return updateProfile(profile);
            } else {
                throw new StorageServiceException(dbiException);
            }
        }
    }

    public Profile updateProfile(final Profile profile)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final Profile baseProfile = profileDao.getByUserId(profile.getUserId());
            if (profile.getFirstName() != null) baseProfile.setFirstName(profile.getFirstName());
            if (profile.getLastName() != null) baseProfile.setLastName(profile.getLastName());
            if (profile.getSummary() != null) baseProfile.setSummary(profile.getSummary());
            if (profile.getContactWechat() != null) baseProfile.setContactWechat(profile.getContactWechat());
            if (profile.getContactEmail() != null) baseProfile.setContactEmail(profile.getContactEmail());
            if (profile.getContactPhone() != null) baseProfile.setContactPhone(profile.getContactPhone());
            if (profile.getWorkCity() != null) baseProfile.setWorkCity(profile.getWorkCity());
            if (profile.getWorkState() != null) baseProfile.setWorkState(profile.getWorkState());
            if (profile.getWorkPosition() != null) baseProfile.setWorkPosition(profile.getWorkPosition());
            if (profile.getWorkCompany() != null) baseProfile.setWorkCompany(profile.getWorkCompany());
            if (profile.getWorkYear() != null) baseProfile.setWorkYear(profile.getWorkYear());
            if (profile.getWorkReferStatus() != null) baseProfile.setWorkReferStatus(profile.getWorkReferStatus());
            if (profile.getWorkReferPosition() != null) baseProfile.setWorkReferPosition(profile.getWorkReferPosition());
            if (profile.getPhoneRate() != null) baseProfile.setPhoneRate(profile.getPhoneRate());
            if (profile.getMeetupRate() != null) baseProfile.setMeetupRate(profile.getMeetupRate());
            if (profile.getMeetupBonus() != null) baseProfile.setMeetupBonus(profile.getMeetupBonus());
            if (profile.getHeadShotUrl() != null) baseProfile.setHeadShotUrl(profile.getHeadShotUrl());
            if (profile.getAvailableTime() != null) baseProfile.setAvailableTime(profile.getAvailableTime());
            if (profile.getPreferTime() != null) baseProfile.setPreferTime(profile.getPreferTime());
            if (profile.getPreferAddress() != null) baseProfile.setPreferAddress(profile.getPreferAddress());
            if (profile.getPreferPayment() != null) baseProfile.setPreferPayment(profile.getPreferPayment());
            if (profile.getTimeZone() != null) baseProfile.setTimeZone(profile.getTimeZone());
            if (profile.getStatus() != null) baseProfile.setStatus(profile.getStatus());

            final int changes = profileDao.update(baseProfile.getUserId(), baseProfile.getFirstName(), baseProfile.getLastName(),
                baseProfile.getGender(), baseProfile.getMajor(), baseProfile.getSummary(), baseProfile.getContactWechat(),
                baseProfile.getContactEmail(), baseProfile.getContactPhone(), baseProfile.getWorkCity(), baseProfile.getWorkState(),
                baseProfile.getWorkAddress(), baseProfile.getWorkCompany(), baseProfile.getWorkPosition(), baseProfile.getWorkYear(),
                baseProfile.getWorkReferStatus(), baseProfile.getWorkReferPosition(), baseProfile.getAvailableTime(),
                baseProfile.getPreferTime(), baseProfile.getPreferPayment(), baseProfile.getPreferAddress(), baseProfile.getPhoneRate(),
                baseProfile.getMeetupRate(), baseProfile.getMeetupBonus(), baseProfile.getHeadShotUrl(), baseProfile.getTimeZone(),
                baseProfile.getStatus(), timeNow);

            if (changes == 0) {
                throw new ItemNotFoundException();
            }
            return getProfileByUserId(profile.getUserId());

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }

    public Profile updateProfileStatus(final Long userId, final Integer updateStatus)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();

            final Profile baseProfile = profileDao.getByUserId(userId);
            final Integer baseStatus = baseProfile.getStatus();

            if (updateStatus.equals(Constant.PROFILE_STATUS_INCOMPLETE)) {
                throw new BadRequestException();
            }

            if (baseStatus.equals(Constant.PROFILE_STATUS_PROVIDER_APPROVED)) {
                throw new DuplicateItemException("The profile has already been approved!");
            }

            if (baseProfile.getStatus().equals(Constant.PROFILE_STATUS_PROVIDER_PENDING) &&
                updateStatus.equals(Constant.PROFILE_STATUS_PROVIDER_PENDING)) {
                throw new DuplicateItemException("The profile is already pending!");
            }

            final int changes = profileDao.updateStatus(userId, updateStatus, timeNow);

            if (changes == 0) {
                throw new ItemNotFoundException();
            }
            return getProfileByUserId(userId);

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }
}
