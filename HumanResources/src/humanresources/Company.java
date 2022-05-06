/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package humanresources;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @authors Rodrigo Santos & João Fernnandes
 * @lastmod 2022-05-06
 */
public class Company {

    private String name;
    private ArrayList<Employee> employees;
    private Values values;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.values = new Values(68.18, 3.25, 0.2);
    }

    /**
     * Altera os valores fixados pela empresa
     *
     * @param workdayValue valor por dia de trabalho
     * @param kilometerValue valor por quilómetro percorrido
     * @param salesPercentage valor da percentagem das vendas realizadas
     */
    public void changeCompanyValues(double workdayValue, double kilometerValue, double salesPercentage) {
        if (workdayValue > 0 && kilometerValue > 0 && salesPercentage > 0) {
            values.setWorkDayValue(workdayValue);
            values.setKilometerValue(kilometerValue);
            values.setSalesPercentage(salesPercentage);
        } else {
            System.out.println("ERRO: Dados inválidos!");
        }
    }

    /**
     * Devolve o total de empregados da empresa
     *
     * @return Tamanho da ArrayList
     */
    public int getTotalEmployees() {
        return employees.size();
    }

    /**
     * Adiciona uma ficha de empregado
     */
    public void addEmployee() {
        InputReader input = new InputReader();
        LocalDate localDate = LocalDate.now();

        int code;
        String name;
        String category;
        Date entryDate;
        Employee newEmployee;

        //leitura do nome
        name = input.getText("Nome");

        //leitura da categoria
        category = input.getText("Categoria");

        //atribuição do código
        code = getTotalEmployees() + 1;

        //atribuição da data de entrada
        entryDate = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());

        switch (category.toUpperCase()) {
            case "GESTOR":
                newEmployee = new Manager(name, code, entryDate);
                break;
            case "MOTORISTA":
                newEmployee = new Driver(name, code, entryDate);
                break;
            case "COMERCIAL":
                newEmployee = new Salesman(name, code, entryDate);
                break;
            default:
                newEmployee = new Employee(name, code, entryDate, "Normal");
                break;
        }

        employees.add(newEmployee);
    }

    /**
     * Devolve o índice de um empregado com um determinado código, se existir
     *
     * @param code Código do empregado
     * @return Índice do empregado na ArrayList (caso exista) ou -1 (caso não
     * exista)
     */
    public int getIndexOfEmployee(int code) {
        for (Employee employee : employees) {
            if (employee.getCode() == code) {
                return employees.indexOf(employee);
            }
        }

        return -1;
    }

    /**
     * Devolve um objeto do tipo Empregado da ArrayList através do seu código
     *
     * @param code Código do empregado
     */
    private Employee getEmployee(int code) {
        int index = getIndexOfEmployee(code);
        if (index == -1) {
            System.out.println("ERRO: O empregado não existe!");
            return null;
        }

        return employees.get(index);
    }

    /**
     * Obtém a ficha de um empegado, através do seu código
     *
     * @param code Código do empregado
     */
    public void employeeRecord(int code) {
        System.out.println(getEmployee(code));
    }

    /**
     * Obtém as fichas de todos os empregados
     */
    public void employeeRecords() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * Obtém as fichas de todos os empregados de uma determinada categoria
     *
     * @param category Categoria de empregados
     */
    private void employeeRecords(String category) {
        for (Employee employee : employees) {
            if (employee.getCategory().toUpperCase().equals(category)) {
                System.out.println(employee);
            }
        }
    }

    /**
     * Obtém as fichas dos empregados, filtrados por categoria
     */
    public void employeeRecordsByCategory() {
        System.out.println("GESTORES:");
        employeeRecords("GESTOR");
        System.out.println("");

        System.out.println("MOTORISTAS:");
        employeeRecords("MOTORISTA");
        System.out.println("");

        System.out.println("COMERCIAIS");
        employeeRecords("COMERCIAL");
        System.out.println("");
    }

    /**
     * Devolve o salário de um empregado
     *
     * @param code Código do empregado
     * @return Valor total do salário
     */
    public double employeeSalary(int code) {
        Employee employee = getEmployee(code);
        double total = 0.0;

        String category = employee.getCategory();

        total += employee.getWorkedDays() * values.getWorkdayValue();
        total += employee.getWorkedDays() * values.getFoodAllowance();
        total += employee.seniority() * values.getSeniorityAward();

        switch (category.toUpperCase()) {
            case "GESTOR":
                total += total * ((Manager) employee).getBonus();
                break;
            case "MOTORISTA":
                total += ((Driver) employee).getKilometers() * values.getKilometerValue();
                break;
            case "COMERCIAL":
                total += ((Salesman) employee).getSales() * values.getSalesPercentage();
                break;
        }

        return total;
    }

    /**
     * Incrementa o número de dias trabalhados dos empregados
     */
    public void increaseWorkedDays() {
        int month = LocalDate.now().getMonthValue();

        for (Employee employee : employees) {
            employee.setWorkedDays(month, employee.getWorkedDays() + 1);
        }
    }

    @Override
    public String toString() {
        return "Empresa: " + name + "\n"
                + "Total de empregados: " + getTotalEmployees() + "\n";
    }
}
