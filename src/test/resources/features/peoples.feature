Feature: people feature

  Scenario: A user gets info about all peoples
    Given the following peoples
      | surname | name | patronymic | dateOfBirth | sex |
      | Хорошилова| Анастасия| Александровна |01.01.2000   | ж   |
      | Громыко   | Максим   | Дмитриевич    | 02.02.2021  |  м  |
      | Иванов    | Иван     | Иванович      | 03.03.2022  | м   |
    When user goes by special endpoint
    Then get list of peoples