package decorators.interface_segregation;

import decorators.Element;
import decorators.wait_strategy.WaitStrategy;

public interface ElementWaitService {
    void wait(Element element, WaitStrategy waitStrategy);
}
