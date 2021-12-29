Feature: passport feature

  Scenario: A user gets info about person by its passport
    Given the following person
      | surname | name | patronymic | dateOfBirth | sex |
      | Fur     | FUrFUr| Furfurfur |01.01.2000   | ж   |
    When user goes by endpoint
    Then get result of the person


  Scenario: A user gets info about passports by name, surname, date of birth
    Given the person
      |id| surname | name | patronymic | dateOfBirth | sex |
      | 1| Хорошилова| Анастасия| Александровна |01.01.2000   | ж   |
    And following passports
      |id|people |personalNumber|documentNumber|authority|dateOfIssue|dateOfExpiry|typeOfPassport|status|
      |1|1      |0000          |111111        |ГУ МВД   |01.02.2020 |01.02.2030  |ID            |ACTIVE|
      |2|1      |0001          |111112        |ГУ МВД   |03.03.2000 |03.03.2016  |INTERNATIONAL | EXPIRED|
    When user gets passports
    Then get result passports
