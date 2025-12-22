-- Produto Fundo Multimercado
INSERT INTO products (id, nome, tipo, rentabilidade, risco, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, valor_maximo, ativo, liquidez_dias, descricao) VALUES
    (103, 'Fundo Multimercado', 'FUNDO_MULTIMERCADO', 0.08, 'MODERADO', 6, 24, 1000, 100000, true, 0, 'Fundo Multimercado diversificado');
-- Simulações do cliente 123
-- INSERT INTO simulacoes (id, clienteId, produto_id, valorInvestido, valorFinal, prazoMeses, dataSimulacao) VALUES
--     (1, 123, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11200.00, 12, TIMESTAMP '2025-10-31 14:00:00'),
--     (2, 123, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5800.00, 6, TIMESTAMP '2025-09-15 10:30:00');

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
-- REMOVIDO: duplicidade de produtos para evitar erro de subquery

-- Inserir simulações
-- Simulações para CDB Caixa 2026 em 2025-10-30

-- Criar clientes diferentes para cada simulação
INSERT INTO clients (id, nome, cpf, email, perfil_risco, pontuacao_risco, preferencia_investimento, volume_total_investido, frequencia_movimentacoes, data_cadastro) VALUES
    (201, 'Cliente 201', '20100000000', 'cliente201@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (202, 'Cliente 202', '20200000000', 'cliente202@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (203, 'Cliente 203', '20300000000', 'cliente203@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (204, 'Cliente 204', '20400000000', 'cliente204@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (205, 'Cliente 205', '20500000000', 'cliente205@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (206, 'Cliente 206', '20600000000', 'cliente206@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (207, 'Cliente 207', '20700000000', 'cliente207@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (208, 'Cliente 208', '20800000000', 'cliente208@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (209, 'Cliente 209', '20900000000', 'cliente209@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (210, 'Cliente 210', '21000000000', 'cliente210@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (211, 'Cliente 211', '21100000000', 'cliente211@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (212, 'Cliente 212', '21200000000', 'cliente212@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (213, 'Cliente 213', '21300000000', 'cliente213@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (214, 'Cliente 214', '21400000000', 'cliente214@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (215, 'Cliente 215', '21500000000', 'cliente215@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (216, 'Cliente 216', '21600000000', 'cliente216@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (217, 'Cliente 217', '21700000000', 'cliente217@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00'),
    (218, 'Cliente 218', '21800000000', 'cliente218@teste.com', 'MODERADO', 65, 'EQUILIBRADO', 50000.00, 10, TIMESTAMP '2024-01-01 10:00:00');

-- 15 simulações para CDB Caixa 2026, clientes 201-215
INSERT INTO simulacoes (id, clienteId, produto_id, valorInvestido, valorFinal, prazoMeses, dataSimulacao) VALUES
    (1, 201, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:00:00'),
    (2, 202, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:10:00'),
    (3, 203, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:20:00'),
    (4, 204, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:30:00'),
    (5, 205, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:40:00'),
    (6, 206, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 10:50:00'),
    (7, 207, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:00:00'),
    (8, 208, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:10:00'),
    (9, 209, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:20:00'),
    (10, 210, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:30:00'),
    (11, 211, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:40:00'),
    (12, 212, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 11:50:00'),
    (13, 213, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 12:00:00'),
    (14, 214, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 12:10:00'),
    (15, 215, (SELECT id FROM products WHERE nome = 'CDB Caixa 2026'), 10000.00, 11050.00, 12, TIMESTAMP '2025-10-30 12:20:00');

-- 8 simulações para Fundo XPTO, clientes 216-223
INSERT INTO simulacoes (id, clienteId, produto_id, valorInvestido, valorFinal, prazoMeses, dataSimulacao) VALUES
    (16, 216, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:00:00'),
    (17, 217, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:10:00'),
    (18, 218, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:20:00'),
    (19, 219, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:30:00'),
    (20, 220, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:40:00'),
    (21, 221, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 13:50:00'),
    (22, 222, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 14:00:00'),
    (23, 223, (SELECT id FROM products WHERE nome = 'Fundo XPTO'), 5000.00, 5700.00, 6, TIMESTAMP '2025-10-30 14:10:00');
