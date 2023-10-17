package com.bin.api.dao.mybatis.model;

public class Guest {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pl_guest.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pl_guest.open_id
     *
     * @mbg.generated
     */
    private String openId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pl_guest.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pl_guest.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pl_guest.branch_id
     *
     * @mbg.generated
     */
    private Long branchId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pl_guest.id
     *
     * @return the value of pl_guest.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pl_guest.id
     *
     * @param id the value for pl_guest.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pl_guest.open_id
     *
     * @return the value of pl_guest.open_id
     *
     * @mbg.generated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pl_guest.open_id
     *
     * @param openId the value for pl_guest.open_id
     *
     * @mbg.generated
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pl_guest.name
     *
     * @return the value of pl_guest.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pl_guest.name
     *
     * @param name the value for pl_guest.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pl_guest.phone
     *
     * @return the value of pl_guest.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pl_guest.phone
     *
     * @param phone the value for pl_guest.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pl_guest.branch_id
     *
     * @return the value of pl_guest.branch_id
     *
     * @mbg.generated
     */
    public Long getBranchId() {
        return branchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pl_guest.branch_id
     *
     * @param branchId the value for pl_guest.branch_id
     *
     * @mbg.generated
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}