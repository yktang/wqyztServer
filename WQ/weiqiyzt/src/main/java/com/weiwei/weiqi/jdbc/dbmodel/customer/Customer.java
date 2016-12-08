package com.weiwei.weiqi.jdbc.dbmodel.customer;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Customers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customers")
public class Customer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3286278551556893515L;
	private Integer id;
	private String contactsName;
	private String password;
	private String userName;
	private String mobilePhonenumber;
	private String phoneNumber;
	private String email;
	private String companyName;
	private String companyAddress;
	private Integer companyCityAddressid;
	private Integer companyProvinceAddressid;
	private Integer companyDistrictAddressid;
	private String isVip;
	private Date registerDate;
	private String geographyAddress;
	private String isAnonymous;
	private String additionalNote;
	private String loginIp;
	private Timestamp lastLoginTime;
	private String otherPlatformLogin;
	private String accountType;
	private String salt;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(String contactsName, String password, String userName,
			String mobilePhonenumber, String phoneNumber, String email,
			String companyName, String companyAddress,
			Integer companyCityAddressid, Integer companyProvinceAddressid,
			Integer companyDistrictAddressid, String isVip, Date registerDate,
			String geographyAddress, String isAnonymous, String additionalNote,
			String loginIp, Timestamp lastLoginTime, String otherPlatformLogin,
			String accountType, String salt) {
		this.contactsName = contactsName;
		this.password = password;
		this.userName = userName;
		this.mobilePhonenumber = mobilePhonenumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyCityAddressid = companyCityAddressid;
		this.companyProvinceAddressid = companyProvinceAddressid;
		this.companyDistrictAddressid = companyDistrictAddressid;
		this.isVip = isVip;
		this.registerDate = registerDate;
		this.geographyAddress = geographyAddress;
		this.isAnonymous = isAnonymous;
		this.additionalNote = additionalNote;
		this.loginIp = loginIp;
		this.lastLoginTime = lastLoginTime;
		this.otherPlatformLogin = otherPlatformLogin;
		this.accountType = accountType;
		this.salt = salt;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "contactsname", length = 100)
	public String getContactsName() {
		return this.contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "mobilephonenumber", length = 50)
	public String getMobilePhonenumber() {
		return this.mobilePhonenumber;
	}

	public void setMobilePhonenumber(String mobilePhonenumber) {
		this.mobilePhonenumber = mobilePhonenumber;
	}

	@Column(name = "phonenumber", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "companyname", length = 150)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "companyaddress")
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "companycityaddressid")
	public Integer getCompanyCityAddressid() {
		return this.companyCityAddressid;
	}

	public void setCompanyCityAddressid(Integer companyCityAddressid) {
		this.companyCityAddressid = companyCityAddressid;
	}

	@Column(name = "companyprovinceaddressid")
	public Integer getCompanyProvinceAddressid() {
		return this.companyProvinceAddressid;
	}

	public void setCompanyProvinceAddressid(Integer companyProvinceAddressid) {
		this.companyProvinceAddressid = companyProvinceAddressid;
	}

	@Column(name = "companydistrictaddressid")
	public Integer getCompanyDistrictAddressid() {
		return this.companyDistrictAddressid;
	}

	public void setCompanyDistrictAddressid(Integer companyDistrictAddressid) {
		this.companyDistrictAddressid = companyDistrictAddressid;
	}

	@Column(name = "isvip", length = 1)
	public String getIsVip() {
		return this.isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "registerdate", length = 0)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "geographyaddress", length = 100)
	public String getGeographyAddress() {
		return this.geographyAddress;
	}

	public void setGeographyAddress(String geographyAddress) {
		this.geographyAddress = geographyAddress;
	}

	@Column(name = "isanonymous", length = 1)
	public String getIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	@Column(name = "additionalnote", length = 500)
	public String getAdditionalNote() {
		return this.additionalNote;
	}

	public void setAdditionalNote(String additionalNote) {
		this.additionalNote = additionalNote;
	}

	@Column(name = "loginip", length = 50)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "lastlogintime", length = 0)
	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "otherplatformlogin", length = 20)
	public String getOtherPlatformLogin() {
		return this.otherPlatformLogin;
	}

	public void setOtherPlatformLogin(String otherPlatformLogin) {
		this.otherPlatformLogin = otherPlatformLogin;
	}

	@Column(name = "accounttype", length = 1)
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "salt", length = 100)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}