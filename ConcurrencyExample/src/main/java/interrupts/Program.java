package interrupts;

/**
 * код взят из статьи на хабре: https://habrahabr.ru/post/164487/
 * */
class Incremenator extends Thread
{
    private volatile boolean mIsIncrement = true;

    public void changeAction()	//Меняет действие на противоположное
    {
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run()
    {
        do
        {
            if(!Thread.interrupted())	//Проверка прерывания
            {
                if(mIsIncrement) Program.mValue++;	//Инкремент
                else Program.mValue--;			//Декремент

                //Вывод текущего значения переменной
                System.out.print(Program.mValue + " ");
            }
            else
                /**
                 * Поток прерван, поток не завершается,
                 * но управление уже передаётся
                 * внешнему потоку(в данном случае главному,
                 * который в свою очередь завершает выполнение программы)
                 * с помощью return
                 * */
                return;

            try{
                Thread.sleep(1000);		//Приостановка потока на 1 сек.
            }catch(InterruptedException e){
                return;	//Завершение потока после прерывания
            }
        }
        while(true);
    }
}

public class Program
{
    //Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    static Incremenator mInc;	//Объект побочного потока

    public static void main(String[] args)
    {
        mInc = new Incremenator();	//Создание потока

        System.out.print("Значение = ");

        mInc.start();	//Запуск потока

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for(int i = 1; i <= 3; i++)
        {
            try{
                Thread.sleep(i*2*1000);		//Ожидание в течении i*2 сек.
            }catch(InterruptedException e){
                System.out.println("\nПоток прерван!");
            }

            mInc.changeAction();	//Переключение действия
        }

        /**
         * Прерывание(ПРЕРЫВАНИЕ, А НЕ ЗАВЕРШЕНИЕ - метод isAlive() выдаст true)
         * побочного потока
         * */
        mInc.interrupt();
        /**
         * Данный метод полностью завершает поток (метод isAlive() выдаст false),
         * но данный метод небезопасен и объявлен как Deprecated
         * */
//        mInc.stop();

        /**
         * безопасно завершить прерванный поток можно с помощью join()
         * */
        try {
            mInc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nПоток жив? -> " + mInc.isAlive());
    }
}
