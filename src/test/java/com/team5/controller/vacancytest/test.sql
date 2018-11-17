DELETE FROM vacancy;

INSERT INTO vacancy (id, idDeveloper, position,
salaryFrom, salaryTo, vacancyState, experienceYearsRequire)
VALUES
(1,	1, 'Java Middle', 1500.00, 2500.00, 'Active', 5.00),
(2,	1, 'C# Senior',	3000.00, 4500.00, 'Active', 4.00),
(3,	1, 'Data Engineer / Python Backend Developer', 3500.00, 5000.00, 'Active', 5.00);