package com.weiwei.weiqi.service.customer.api;

import com.weiwei.common.exception.BusinessException;
import com.weiwei.weiqi.request.login.LoginEnter;
import com.weiwei.weiqi.response.base.GeneralResult;

public interface CustomerService {

	GeneralResult login(LoginEnter loginEnter) throws BusinessException;

}
