-- Populando a tabela Instituicao
INSERT INTO instituicao (nome, email, senha) VALUES ('Instituto de Tecnologia', 'contato@tecnologia.edu', 'senhaInstituto1');

-- Populando a tabela Monitor
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Ana Pereira', 'ana@example.com', 'senha123', 3);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Carlos Silva', 'carlos@example.com', 'senha123', 4);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Laura Martins', 'laura@example.com', 'senha123', 5);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Paulo Lima', 'paulo@example.com', 'senha123', 1);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Fernanda Costa', 'fernanda@example.com', 'senha123', 3);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Rafael Alves', 'rafael@example.com', 'senha123', 5);
INSERT INTO monitor (nome, email, senha, avaliacao) VALUES ('Mariana Oliveira', 'mariana@example.com', 'senha123', 4);

-- Populando a tabela Aluno
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('João Silva', 'joao@example.com', 'senha123', 3);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Maria Souza', 'maria@example.com', 'senha123', 3);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Bruno Carvalho', 'bruno@example.com', 'senha123', 4);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Carla Rodrigues', 'carla@example.com', 'senha123', 1);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Pedro Santos', 'pedro@example.com', 'senha123', 3);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Ana Lima', 'ana.lima@example.com', 'senha123', 3);
INSERT INTO aluno (nome, email, senha, avaliacao) VALUES ('Lucas Gomes', 'lucas@example.com', 'senha123', 2);

-- Populando a tabela Disciplina
INSERT INTO disciplina (nome) VALUES ('Matemática');
INSERT INTO disciplina (nome) VALUES ('Física');
INSERT INTO disciplina (nome) VALUES ('Programação');

-- Associando Monitores e Disciplinas
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (1, 1); -- Ana Pereira ensina Matemática
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (1, 2); -- Ana Pereira ensina Física
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (2, 3); -- Carlos Silva ensina Programação
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (3, 1); -- Laura Martins ensina Matemática
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (4, 2); -- Paulo Lima ensina Física
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (5, 3); -- Fernanda Costa ensina Programação
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (6, 1); -- Rafael Alves ensina Matemática
INSERT INTO monitor_disciplina (monitor_id, disciplina_id) VALUES (7, 2); -- Mariana Oliveira ensina Física

-- Associando Alunos e Disciplinas
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (1, 1); -- João Silva cursa Matemática
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (2, 3); -- Maria Souza cursa Programação
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (3, 2); -- Bruno Carvalho cursa Física
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (4, 1); -- Carla Rodrigues cursa Matemática
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (5, 3); -- Pedro Santos cursa Programação
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (6, 1); -- Ana Lima cursa Matemática
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (7, 2); -- Lucas Gomes cursa Física

-- Populando a tabela Monitoria com 12 registros
INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-01 14:00:00', 'Biblioteca Central', 'confirmada', 1, 1, 1);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-01 14:00:00', 'Laboratório de Informática', 'confirmada', 2, 2, 3);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-02 09:00:00', 'Sala 101', 'confirmada', 3, 3, 2);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-02 10:30:00', 'Sala 202', 'confirmada', 4, 4, 1);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-03 08:00:00', 'Biblioteca Central', 'confirmada', 5, 5, 3);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-03 11:00:00', 'Sala 303', 'confirmada', 6, 6, 1);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-04 15:00:00', 'Laboratório de Física', 'confirmada', 7, 7, 2);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-04 16:00:00', 'Biblioteca Central', 'confirmada', 1, 4, 1);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-05 09:30:00', 'Sala 404', 'confirmada', 2, 5, 3);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-05 14:00:00', 'Sala 505', 'confirmada', 3, 6, 2);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-06 08:30:00', 'Sala 606', 'confirmada', 4, 7, 1);

INSERT INTO monitoria (data, local, status, monitor_id, aluno_id, disciplina_id)
VALUES ('2024-09-06 10:00:00', 'Biblioteca Central', 'confirmada', 5, 1, 3);
