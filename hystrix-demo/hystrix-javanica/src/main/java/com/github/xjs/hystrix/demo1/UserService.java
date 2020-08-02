package com.github.xjs.hystrix.demo1;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

public class UserService {

    @HystrixCommand(fallbackMethod = "fallback")
    public User getUserById(String id) {
        throw new RuntimeException("hystrix-javanina-fallback");
    }

    @HystrixCommand
    public Future<User> getUserByName(String name) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return new User("id", name);
            }
        };
    }

    @HystrixCommand
    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(new User("id", "name" ));
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    public User fallback(String id, Throwable e){
        System.out.println(e.getClass().getName());
        return new User(id, e.getMessage());
    }
}
