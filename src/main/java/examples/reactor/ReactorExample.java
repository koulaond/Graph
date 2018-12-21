package examples.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorExample {
  public static void main(String[] args) {
    Flux<Integer> intFlux = Flux.just(1, 2, 3, 4, 5)
            .log()
            .doOnComplete(() -> System.out.println("Completed"))
            .publish().autoConnect();

    Flux<Integer> zippedFlux = Flux.just(2, 4, 6, 8, 10)
            .log()
            .zipWith(intFlux, (one, two) -> one + two)
            .publish().autoConnect();
    zippedFlux.subscribe(integer -> System.out.println("subscriber for zipped flux " + integer));
  }
}
