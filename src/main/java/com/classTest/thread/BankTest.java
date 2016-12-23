package com.classTest.thread;

/**
 * Created by root on 16-12-22.
 */
public class BankTest {
    public class Bank{
        //使用ThreadLocal类管理共享变量account
        private ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue(){
                return 100;
            }
        };
        public void save(int money){
            account.set(account.get()+money);
        }
        public int getAccount(){
            return account.get();
        }
    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.save1(10);
                bank.save(10);
                System.out.println(i + "账户余额为：" + bank.getAccount());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        BankTest st = new BankTest();
        st.useThread();
    }

}
