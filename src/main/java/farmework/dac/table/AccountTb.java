package farmework.dac.table;

public class AccountTb extends AbstractTable {

    public AccountTb() {
        super("course", "tb_account");
    }

    public static AccountTb of() {
        return new AccountTb();
    }
}
