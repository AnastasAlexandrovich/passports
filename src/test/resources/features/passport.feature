Feature: passport feature

  Scenario: A user gets info about person by its passport
    Given the following person
      | surname | name | patronymic | dateOfBirth | sex |
      | Fur     | FUrFUr| Furfurfur |01.01.2000   | ж   |
    When user goes by endpoint
    Then get result of the person
