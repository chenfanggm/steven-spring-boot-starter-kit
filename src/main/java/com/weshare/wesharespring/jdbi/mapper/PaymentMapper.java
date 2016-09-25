package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Payment;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ResultSetMapper<Payment> {

	@Override
	public Payment map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        return Payment
			.builder()
			.paymentId(r.getLong("payment_id"))
			.userId(r.getLong("user_id"))
			.paymentType(r.getString("payment_type"))
			.paymentAccount(r.getString("payment_account"))
			.status(r.getInt("status"))
			.timeCreated(r.getLong("time_created"))
			.timeUpdated(r.getLong("time_updated"))
			.build();
	}

}
