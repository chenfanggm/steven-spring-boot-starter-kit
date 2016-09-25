package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.constant.Role;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.PreConditionFailedException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Request.PasswordRequest;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.jdbi.dao.UserDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUserId(final Long userId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final User user = userDao.getById(userId);
            if (user == null) {
                throw new ItemNotFoundException();
            }
            return user;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public User getUserByEmail(final String email)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final User user = userDao.getByEmail(email);
            if (user == null) {
                throw new ItemNotFoundException();
            }
            return user;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public User createUser(final String email, final String password)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final Long userId = userDao.create(email, password, Constant.USER_NOT_VERIFIED,
                Role.USER.getValue(), Constant.USER_STATUS_ACTIVE, timeNow);
            return getUserByUserId(userId);

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }

    public void updatePassword(final PasswordRequest passwordRequest, final Long userId)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final User user = userDao.getById(userId);
            if (user == null) {
                throw new ItemNotFoundException();
            }

            // compare userId
            if (!Utils.isValidUserPassword(user, passwordRequest.getOldPassword())) {
                logger.warn("<In> updatePassword(): Invalid password for User: {}", user.getEmail());
                throw new PreConditionFailedException();
            }


            final Long timeNow = Utils.getCurrentTimeStamp();
            final String newHash = Utils.getPasswordHash(passwordRequest.getNewPassword());

            int changes = userDao.updatePasswordById(userId, newHash, timeNow);
            if (changes == 0) {
                throw new ItemNotFoundException();
            }

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }


}
