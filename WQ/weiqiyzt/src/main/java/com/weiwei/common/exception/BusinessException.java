package com.weiwei.common.exception;

import com.weiwei.common.constants.enums.ResultCodeEnum;

public class BusinessException extends RuntimeException{
	  private static final long serialVersionUID = 345573995122478L;
	    /**
	     * 错误代码
	     */
	    private int errorCode = 0;
	    
	    private String detailMsg = "";
	    /**
	     * 可替换变量,相当于占位符
	     */
	    private String[] variables = null;
	    
	    public BusinessException() {
			super();
		}
	    
		/**
	     * 使用错误码定义BusinessException
	     *
	     * @param errorCode 错误码
	     */
	    public BusinessException(ResultCodeEnum  rcEnum) {
	        super(rcEnum.getDesc());
	        this.errorCode = rcEnum.getCode();
	    }
	    
	    public BusinessException(ResultCodeEnum  rcEnum,String detailmsg) {
			super(rcEnum.getDesc());
			 this.errorCode = rcEnum.getCode();
			 this.detailMsg = detailmsg;
		}
	    
		/**
	     * 使用错误码定义BusinessException
	     *
	     * @param errorCode 错误码
	     */
	    public BusinessException(int errorCode) {
	        this.errorCode = errorCode;
	    }

	    /**
	     * 使用错误码、提示信息的变量定义BusinessException
	     *
	     * @param errorCode 错误码
	     * @param variables 用于在提示信息中展示的参数
	     */
	    public BusinessException(int errorCode, String[] variables) {
	        this.errorCode = errorCode;
	        this.variables = (variables == null ? null : variables.clone());
	    }

	    /**
	     * 使用错误码、默认提示定义BusinessException
	     *
	     * @param errorCode    错误码
	     * @param errorMessage 默认错误信息
	     */
	    public BusinessException(int errorCode, String errorMessage) {
	        super(errorMessage);
	        this.errorCode = errorCode;
	    }

	    /**
	     * 使用错误码、默认提示、提示信息的变量定义BusinessException
	     *
	     * @param errorCode    错误码
	     * @param errorMessage 默认错误信息
	     * @param variables    用于在提示信息中展示的参数
	     */
	    public BusinessException(int errorCode, String errorMessage, String[] variables) {
	        super(errorMessage);
	        this.errorCode = errorCode;
	        this.variables = (variables == null ? null : variables.clone());
	    }

	    /**
	     * 将异常封装为BusinessException，并指定错误码
	     *
	     * @param errorCode 错误码
	     * @param t         源异常
	     */
	    public BusinessException(int errorCode, Throwable t) {
	        super(t);
	        this.errorCode = errorCode;
	    }

	    /**
	     * 将异常封装为BusinessException，并指定错误码和默认提示
	     *
	     * @param errorCode    错误码
	     * @param errorMessage 错误描述
	     * @param t            源异常
	     */
	    public BusinessException(int errorCode, String errorMessage, Throwable t) {
	        super(errorMessage, t);
	        this.errorCode = errorCode;
	    }

	    /**
	     * 将异常封装为BusinessException，并指定错误码和提示信息的变量
	     *
	     * @param errorCode 错误码
	     * @param variables 参数
	     * @param t         源异常
	     */
	    public BusinessException(int errorCode, String[] variables, Throwable t) {
	        super(t);
	        this.errorCode = errorCode;
	        this.variables = (variables == null ? null : variables.clone());
	    }

	    /**
	     * 将异常封装为BusinessException，并指定错误码、默认提示、提示信息的变量
	     *
	     * @param errorCode    错误码
	     * @param errorMessage 默认错误信息
	     * @param variables    用于在提示信息中展示的参数
	     * @param t            源异常
	     */
	    public BusinessException(int errorCode, String errorMessage, String[] variables, Throwable t) {
	        super(errorMessage, t);
	        this.errorCode = errorCode;
	        this.variables = (variables == null ? null : variables.clone());
	    }

	    /**
	     * 获取错误码
	     *
	     * @return 错误码
	     */
	    public int getErrorCode() {
	        return this.errorCode;
	    }
	    
	    public BusinessException(Throwable cause) {
			super(cause);
		}

	    
		public String getDetailMsg() {
			return detailMsg;
		}

		/**
	     * 异常堆栈增加错误代码和绑定变量
	     *
	     * @return 错误信息
	     */
	    public String toString() {
	        StringBuffer sb = new StringBuffer();
	        sb.append("errorCode[").append(this.errorCode).append("]\n");
	        if (variables != null && variables.length != 0) {
	            sb.append("variables[");
	            for (int i = 0; i < variables.length; i++) {
	                sb.append(variables[i]);
	            }
	            sb.append("]\n");
	        }
	        sb.append(super.toString());
	        return sb.toString();
	    }
}
