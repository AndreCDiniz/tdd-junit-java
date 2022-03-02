import br.com.alura.tdd.model.Funcionario;
import br.com.alura.tdd.service.BonusService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto(){
        BonusService service = new BonusService();

        //Forma de fazer testes com exception
        //assertThrows(IllegalArgumentException.class,
        //        () -> service.calcularBonus(new Funcionario("Andre", LocalDate.now(), new BigDecimal("25000"))));

        //Segunda forma de fazer testes com exception caso precise pegar a mensagem do erro!
        try{
            service.calcularBonus(new Funcionario("Andre", LocalDate.now(), new BigDecimal("25000")));
            fail("Não deu a exception!");
        } catch (Exception e){
            assertEquals("Funcionario com salário maior do que R$10.000 nào pode receber bônus", e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSerDezPorCentoDoSalario(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Andre", LocalDate.now(), new BigDecimal("5000")));

        assertEquals(new BigDecimal("500.00"), bonus);
    }

    @Test
    void bonusBonusDeveriaSerDezPorCentoParaSalarioDeExatamento10000(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Andre", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"), bonus);
    }

}
