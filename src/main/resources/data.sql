
-- Cliente para o endpoint /perfil-risco/{clienteId}
INSERT INTO clients (id, nome, cpf, email, perfil_risco, pontuacao_risco, preferencia_investimento, volume_total_investido, frequencia_movimentacoes, data_cadastro) VALUES
	(123, 'Cliente Teste', '12345678900', 'cliente@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00');

-- Produtos recomendados para o endpoint /produtos-recomendados/{perfil}
INSERT INTO products (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, valor_maximo, ativo, liquidez_dias, descricao) VALUES
	(101, 'CDB Caixa 2026', 'CDB', 0.12, 'BAIXO', 12, 24, 1000, 100000, true, 30, 'CDB Caixa vencimento 2026'),
	(102, 'Fundo XPTO', 'FUNDO', 0.18, 'ALTO', 6, 12, 500, 50000, true, 0, 'Fundo de investimento XPTO');

-- Investimentos para o endpoint /investimentos/{clienteId}
INSERT INTO investments (id, client_id, tipo, valor, rentabilidade, data, status, prazo_meses) VALUES
	(1, 123, 'CDB', 5000.00, 0.12, DATE '2025-01-15', 'ATIVO', 12),
	(2, 123, 'FUNDO_MULTIMERCADO', 3000.00, 0.08, DATE '2025-03-10', 'ATIVO', 12);

-- Telemetria para o endpoint /telemetria
INSERT INTO telemetria (id, servico, quantidadeChamadas, somaTempoRespostaMs, periodoInicio, periodoFim) VALUES
	(1, 'simular-investimento', 120, 30000, TIMESTAMP '2025-10-01 00:00:00', TIMESTAMP '2025-10-31 23:59:59'),
	(2, 'perfil-risco', 80, 14400, TIMESTAMP '2025-10-01 00:00:00', TIMESTAMP '2025-10-31 23:59:59');
-- Script de inicialização do banco de dados (H2/Panache)
-- Senha para todos usuários: password123

-- Inserir usuários de teste (usando sequência)
INSERT INTO users (id, username, password, email, role, enabled) VALUES
(NEXT VALUE FOR users_SEQ, 'admin', '$2a$12$k7ebzNvmKCDiKFwxZX0yhueJOtxxfjOL8/Q6rw1rcwobieWCc3Y7S', 'admin@caixa.com', 'ADMIN', true),
(NEXT VALUE FOR users_SEQ, 'user', '$2a$12$k7ebzNvmKCDiKFwxZX0yhueJOtxxfjOL8/Q6rw1rcwobieWCc3Y7S', 'user@caixa.com', 'USER', true);

-- Inserir produtos necessários para as simulações
INSERT INTO products (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, valor_maximo, ativo, liquidez_dias, descricao) VALUES
	(NEXT VALUE FOR products_SEQ, 'CDB Caixa 2026', 'CDB', 0.12, 'BAIXO', 12, 24, 1000, 100000, true, 30, 'CDB Caixa vencimento 2026'),
	(NEXT VALUE FOR products_SEQ, 'Fundo XPTO', 'FUNDO', 0.16, 'MEDIO', 6, 12, 500, 50000, true, 0, 'Fundo de investimento XPTO');

-- Inserir simulações
-- Simulações para CDB Caixa 2026 em 2025-10-30
INSERT INTO simulacoes (id, clienteId, produto_id, valorInvestido, valorFinal, prazoMeses, dataSimulacao) VALUES
	(1, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11000.00, 12, TIMESTAMP '2025-10-30 10:00:00'),
	(2, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 12000.00, 11100.00, 12, TIMESTAMP '2025-10-30 11:00:00'),
	(3, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 9000.00, 11050.00, 12, TIMESTAMP '2025-10-30 12:00:00'),
	(4, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 11000.00, 11050.00, 12, TIMESTAMP '2025-10-30 13:00:00'),
	(5, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10500.00, 11050.00, 12, TIMESTAMP '2025-10-30 14:00:00'),
	(6, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 15:00:00'),
	(7, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 9500.00, 11050.00, 12, TIMESTAMP '2025-10-30 16:00:00'),
	(8, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 11500.00, 11050.00, 12, TIMESTAMP '2025-10-30 17:00:00'),
	(9, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 18:00:00'),
	(10, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 19:00:00'),
	(11, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 20:00:00'),
	(12, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 21:00:00'),
	(13, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 22:00:00'),
	(14, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 23:00:00'),
	(15, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 23:30:00');

-- Simulações para Fundo XPTO em 2025-10-30
INSERT INTO simulacoes (id, clienteId, produto_id, valorInvestido, valorFinal, prazoMeses, dataSimulacao) VALUES
	(16, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 10:00:00'),
	(17, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5100.00, 5700.00, 6, TIMESTAMP '2025-10-30 11:00:00'),
	(18, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5200.00, 5700.00, 6, TIMESTAMP '2025-10-30 12:00:00'),
	(19, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5300.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:00:00'),
	(20, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5400.00, 5700.00, 6, TIMESTAMP '2025-10-30 14:00:00'),
	(21, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5500.00, 5700.00, 6, TIMESTAMP '2025-10-30 15:00:00'),
	(22, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5600.00, 5700.00, 6, TIMESTAMP '2025-10-30 16:00:00'),
	(23, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5700.00, 5700.00, 6, TIMESTAMP '2025-10-30 17:00:00');
