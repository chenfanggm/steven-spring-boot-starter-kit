package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Appointment;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = RouteConfig.APPOINTMENT_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET)
    public Appointment getAppointmentById(@PathVariable("appointmentId") final Long appointmentId)
        throws BaseServiceException {

        logger.info("<Start> getAppointmentById(): AppointmentId: {}", appointmentId);
        final Appointment appointment =  appointmentService.getAppointmentById(appointmentId);
        logger.info("<End> getAppointmentById(): AppointmentId: {}", appointmentId);
        return appointment;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Appointment> getAppointmentByCurrentUser(@LoggedUser final User user )
        throws BaseServiceException {

        logger.info("<Start> getAppointmentByCurrentUser(): UserId: {}", user.getUserId());
        final List<Appointment> appointmentList =  appointmentService.getAppointmentByUserId(user.getUserId());
        logger.info("<End> getAppointmentByCurrentUser(): UserId: {}", user.getUserId());
        return appointmentList;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<Appointment> getAppointmentByUserId(@PathVariable("userId") final Long userId)
        throws BaseServiceException {

        logger.info("<Start> getAppointmentByUserId(): UserId: {}", userId);
        final List<Appointment> appointmentList =  appointmentService.getAppointmentByUserId(userId);
        logger.info("<End> getAppointmentByUserId(): UserId: {}", userId);
        return appointmentList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Appointment createAppointment(@RequestBody final Appointment appointment, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> createAppointment()");
        appointment.setUserId(user.getUserId());
        final Appointment appointmentResult = appointmentService.createAppointment(appointment);
        logger.info("<End> createAppointment()");

        return appointmentResult;
    }
}
