package playground.mapping.pg.rv1.one2many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.department FROM Employee e WHERE e.name = :employeeName")
    Department findDepartmentByEmployeeName(@Param("employeeName") String employeeName);
}
