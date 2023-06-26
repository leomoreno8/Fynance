describe('Sign up test', () => {
  it('Redirect to signup page', () => {
    cy.visit('http://localhost:3000')

    cy.contains('SIGN UP').click()

    cy.url().should('include', '/signup')
  })

  it('Should enter all fields correctly in the signup fields', () => {
    cy.visit('http://localhost:3000')

    cy.contains('SIGN UP').click()

    cy.url().should('include', '/signup')

    cy.get('input[placeholder="Username"]').type('sinistro')
    cy.get('input[placeholder="Name"]').type('Leonardo')
    cy.get('input[placeholder="Email"]').type('fake@email.com')
    cy.get('input[placeholder="Password"]').type('123')

    cy.get('input[placeholder="Username"]').should('have.value', 'sinistro')
    cy.get('input[placeholder="Name"]').should('have.value', 'Leonardo')
    cy.get('input[placeholder="Email"]').should('have.value', 'fake@email.com')
    cy.get('input[placeholder="Password"]').should('have.value', '123')
  })

  it('Should send request in signup route with all valid fields', () => {
    cy.visit('http://localhost:3000')

    cy.contains('SIGN UP').click()

    cy.url().should('include', '/signup')

    cy.get('input[placeholder="Username"]').type('sinistro')
    cy.get('input[placeholder="Name"]').type('Leonardo')
    cy.get('input[placeholder="Email"]').type('fake@email.com')
    cy.get('input[placeholder="Password"]').type('Abcdefghi1%')

    cy.get('input[placeholder="Username"]').should('have.value', 'sinistro')
    cy.get('input[placeholder="Name"]').should('have.value', 'Leonardo')
    cy.get('input[placeholder="Email"]').should('have.value', 'fake@email.com')
    cy.get('input[placeholder="Password"]').should('have.value', 'Abcdefghi1%')

    cy.intercept('POST', 'http://localhost:8080/users/create', (req) => {
      req.reply({ status: 'success' })
    }).as('signup')    

    cy.get('button[type="submit"]').click()
    
    cy.contains('User created successfully!').should('be.visible')

    cy.wait(7000)

    cy.url().should('include', '/')

  })
})