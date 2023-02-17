Feature: Login
  Scenario: Login
    Given I am a valid user
    When I try to login with <userName> & <password>
    Then Ishould be able to login successfully

    Examples:
    | userName | password |
    |Admin     | admin123 |