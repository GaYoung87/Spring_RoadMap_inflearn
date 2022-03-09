package hello.core.order;

public interface OrderService {

    // 역할을 만듦
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
