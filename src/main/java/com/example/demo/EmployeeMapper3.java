package com.example.demo;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper3 {
    @Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.companyId}, #{employee.name}, #{employee.address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")   // return값이 0, 1이 아니라 자동생성된 id값을 같이 리턴해준다.
    int insert(@Param("employee") Employee employee);

    @Select("SELECT * FROM employee")
    @Results(id = "EmployeeMap", value = {           // id를 설정해주면 복붙 안 해도 재사용이 가능하다.
            @Result(property = "name", column = "employee_name"),
            @Result(property = "address", column = "employee_address")
    })
    List<Employee> getAll();

    @Select("SELECT * FROM employee WHERE id=#{id}")
    @ResultMap("EmployeeMap")
    Employee getById(@Param("id") int id);

    @Select("SELECT * FROM employee WHERE company_id=#{companyId}")
    @ResultMap("EmployeeMap")
    List<Employee> getByCompanyId(@Param("companyId") int companyId);

}
