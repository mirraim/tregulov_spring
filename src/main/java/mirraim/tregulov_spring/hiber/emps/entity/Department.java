package mirraim.tregulov_spring.hiber.emps.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "min_salary")
    private int minSalary;

    // FetchType указывает на метод загрузки списка сущностей Many.
    // EAGER  - список сущностей Many загружается автоматически
    // при сериализации сущности One
    // LASY - список сущностей Many загружается  в момент обращения к этому списку.
    // При этом загрузка должна происходить внутри session
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                            CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER,
            mappedBy = "department")
    private List<Employee> employees;

    public Department() {
    }

    public Department(String departmentName, int maxSalary, int minSalary) {
        this.departmentName = departmentName;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
        employee.setDepartment(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("id=").append(id);
        sb.append(", departmentName='").append(departmentName).append('\'');
        sb.append(", maxSalary=").append(maxSalary);
        sb.append(", minSalary=").append(minSalary);
        sb.append('}');
        return sb.toString();
    }
}
