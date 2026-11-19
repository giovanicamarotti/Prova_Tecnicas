# Respostas — Sistema de Reserva de Laboratorio

Nome completo: Giovani Camarotti Siqueira RA: ______________

> Preencha **todos** os campos. Campo vazio é campo não avaliado.
> Não apague os títulos nem os rótulos em negrito: a correção procura por eles.

---

# Etapa 1 — Diagnóstico

## Problema 1 — a conversão do inventario patrimonial

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
O problema consiste no fato de as classes de domínio do sistema (como Laboratorio e Reserva) estão realizando diretamente o registro do inventário patrimonial externo para os objetos internos (como Equipamento ou ItemLaboratorio). Quando o sistema cresce e novos tipos de bens patrimoniais são adquiridos, ou alterados, o código de conversão espalhado pelas classes superiores precisam ser alteradas com frequência assim comprometendo as regras do negócio.


**Princípio SOLID violado:**
Princípio da Responsabilidade Única e OCP


**Classes afetadas (pelo nome):**
Laboratorio, Reserva (ou ReservaLaboratorio) e ItemPatrimonio/InventarioPatrimonial.

**Que outra dependência indevida essas classes têm em relação ao
inventario patrimonial, e por que ela é um problema:**
Essas classes têm dependência direta de implementação com relação a classes concretas e aos tipos de dados proprietários do sistema de inventário patrimonial, ao invés de dependerem de uma abstração ou interface própria do domínio de reserva de laboratório. Ela integra o núcleo de negócio a detalhes da implementação e esquemas de persistência. Como consequência, qualquer modificação quebra a camada de domínio do sistema.

## Problema 2 — o ciclo de vida de Reserva

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
O problema consiste no controle do ciclo de vida da Reserva sendo realizado de maneira procedural e condicional dentro da própria entidade, assim controlando seu status por meio de variáveis de estado primitivas ou enums acopladas a estruturas de controle como repetições de if/else em métodos de operação. Quando o sistema cresce e novos estados são adicionados ou novas regras de transição são definidas, os condicionais existentes precisam ser abertos e reeditados. Resulta em um código redundante, inconsistências no controle de transições inválidas e erros em códigos já cementados.

**Princípio SOLID violado:**
Responsabilidade única e OCP

**Classes afetadas (pelo nome):**
Reserva e classes relacionadas em sua operação.

---

# Etapa 4 — Justificativa

## Padrão aplicado ao Problema 1

**O problema que ele resolve (descreva antes de nomear o padrão):**
Resolve a incompatibilidade entre a interface e a estrutura de dados exposta pelo sistema externo de inventário patrimonial e a interface esperada do domínio de reserva, atuando como tradutor intermediário que convertendo solicitações e dados de um modelo para o outro sem exigir que nenhuma das duas partes modifiquem suas interfaces.

**Nome do padrão:**
Adapter

**Alternativa descartada e por que ela não resolveria por completo:**
Alterar diretamente as classes do sistema de Inventário Patrimonial, pois assim outros módulos do sistema seriam "quebrados", além de que: caso fossem utilizados  classes estáticas elas manteriam o acoplamento direto sobre o domínio.
## Padrão aplicado ao Problema 2

**O problema que ele resolve (descreva antes de nomear o padrão):**
Resolve o problema de permitir que um objeto altere seu próprio comportamento quando seu estado interno se modifica, eliminando blocos condicionais, distribuindo assim a validação em classes polimórficas separadas que se juntam em uma única interface.

**Nome do padrão:**
State

**Alternativa descartada e por que ela não resolveria por completo:**
A alternativa descartada foi utilizar herança clássica com subclasses concretas de Reserva ou colocar a lógica de comportamento dentro dos métodos de um enum. A herança por subclasses de Reserva não resolve pois na orientação a objetos baseada em classes uma instância não muda seu tipo de execução. Por outro lado, concentrar lógica de negócio dentro das constantes de um enum não resolveria por completo pois ainda manteria todo o ciclo de vida fechado em um único arquivo, violando o OCP, além de dificultar o acesso a manipulação do contexto e injeção de dependências.