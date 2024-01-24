class callme{
    void call(String msg){
        System.out.println("[" + msg);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("interrupted");
        }
    }
}
class caller implements runnable{
    String msg;
    callme target;
    Thread t;
    caller(callme targ,String s){
       target=targ;
       msg=s;
       t=new Thread();
    }
    public void run(){
        synchronized(target){
            target.call(msg);
        }
    }
}
class f1{
    public static void main(String args[]){
        callme target=new callme();
        caller ob1=new caller(target,"hello");
        caller ob2=new caller(target,"synchronized");
        caller ob3=new caller(target,"world");

        ob1.start();
        ob2.start();
        ob3.start();

        try{
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch(InterruptedException e){
            System.out.println("interrupted");
        }
        
    }
}