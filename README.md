# Fynance

- Aplicação de controle financeiro.
- Organize suas compras, gastos, investimentos ou qualquer entrada/saída que você tenha em suas contas.



# USER
- id
- username
- displayName
- Email
- password (hash)
- CONTAS[]

# CONTA / CARTEIRA
- id
- nome
- tipo (Corrente/Poupança/Investimento/Salário/Etc)
- numero
- agência
- banco
- saldo


# MOVIMENTAÇÃO
- ID (da movimentação)
- CONTA (id da conta)
- DataMovimentacao
- Valor
- ES (entrada/saida)
- Descricao
- Categoria (Lazer/Compras/Educação/Transferência/Outro)
- Taxas
- Total