package org.opensrp.repository.postgres.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.opensrp.domain.postgres.Report;
import org.opensrp.domain.postgres.ReportExample;

public interface ReportMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	long countByExample(ReportExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int deleteByExample(ReportExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int insert(Report record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int insertSelective(Report record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	List<Report> selectByExample(ReportExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	Report selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int updateByPrimaryKeySelective(Report record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.report
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	int updateByPrimaryKey(Report record);
}