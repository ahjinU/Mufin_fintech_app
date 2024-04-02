'use client';

import { MoneyShow } from '@/components';
import { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';
import { commaNum } from '@/utils/commaNum';

interface OrderData {
  price: number;
  buyOrderCnt: number;
  sellOrderCnt: number;
}

interface StockData {
  price: number;
  stockOrderList: OrderData[];
}

export function StockCall({ companyName }: { companyName: string }) {
  const [data, setData] = useState<StockData>({
    price: 0,
    stockOrderList: [
      {
        price: 0,
        buyOrderCnt: 0,
        sellOrderCnt: 0,
      },
    ],
  });

  const totalSellOrder = data.stockOrderList.reduce(
    (acc, cur) => acc + cur.sellOrderCnt,
    0,
  );
  const totalBuyOrder = data.stockOrderList.reduce(
    (acc, cur) => acc + cur.buyOrderCnt,
    0,
  );
  const cntOrderRateStyle = (orderCnt: number, comparedTotalCnt: number) => {
    return orderCnt === comparedTotalCnt
      ? 'w-full'
      : orderCnt / comparedTotalCnt <= 0.2
      ? 'w-1/5'
      : orderCnt / comparedTotalCnt <= 0.4
      ? 'w-2/5'
      : orderCnt / comparedTotalCnt <= 0.6
      ? 'w-3/5'
      : 'w-4/5';
  };

  const client = useRef<CompatClient | null>(null);

  const connectHandler = () => {
    const socket = new SockJS('https://mufin.life/api/ws-connection', null, {
      transports: ['websocket', 'xhr-streaming', 'xhr-polling'],
    });
    client.current = Stomp.over(function () {
      return socket;
    });

    client.current?.connect(
      {},
      () => {
        client.current?.send(`/pub/orders/${companyName}`, {});
        client.current?.subscribe(
          `/sub/orders/${companyName}`,
          (message: any) => {
            console.log(JSON.parse(message.body));
            setData(JSON.parse(message.body));
          },
        );
      },
      {},
    );
  };

  useEffect(() => {
    connectHandler();
    return () => client.current?.disconnect();
  }, []);

  return (
    <section className="flex flex-col gap-[1rem]">
      <h3 className="custom-semibold-text">호가</h3>

      <MoneyShow
        mode="DIVIDED_GRAY"
        money={[
          commaNum(totalSellOrder).toString(),
          commaNum(totalBuyOrder).toString(),
        ]}
        moneyColor={['text-custom-blue', 'text-custom-red']}
        text={['판매 대기', '구매 대기']}
        unit="주"
      />

      {(totalBuyOrder !== 0 || totalSellOrder !== 0) && (
        <table className="table table-fixed border-separate border-spacing-y-[0.5rem]">
          <thead className="custom-medium-text text-center">
            <tr>
              <th>판매 호가량</th>
              <th></th>
              <th>구매 호가량</th>
            </tr>
          </thead>
          <tbody className="custom-medium-text text-center">
            {data.stockOrderList.map((stockOrder, index) => {
              return (
                <tr key={`stockOrder-${index}`}>
                  {stockOrder.sellOrderCnt > 0 ? (
                    <td className="text-custom-black flex justify-end">
                      <div
                        className={`${cntOrderRateStyle(
                          stockOrder.sellOrderCnt,
                          totalSellOrder,
                        )} bg-custom-blue-with-opacity rounded-l-[0.8rem] px-[0.5rem]`}
                      >
                        {commaNum(stockOrder.sellOrderCnt)}
                      </div>
                    </td>
                  ) : (
                    <td className="text-custom-black"></td>
                  )}
                  <td
                    className={
                      stockOrder.price === data.price
                        ? 'text-custom-purple'
                        : stockOrder.price === data.stockOrderList[0].price &&
                          stockOrder.price > data.price
                        ? 'text-custom-red'
                        : stockOrder.price ===
                            data.stockOrderList[data.stockOrderList.length - 1]
                              .price && stockOrder.price < data.price
                        ? 'text-custom-blue'
                        : 'text-custom-black'
                    }
                  >
                    {commaNum(stockOrder.price)}
                  </td>
                  {stockOrder.buyOrderCnt > 0 ? (
                    <td className="text-custom-black">
                      <div
                        className={`${cntOrderRateStyle(
                          stockOrder.buyOrderCnt,
                          totalBuyOrder,
                        )} bg-custom-red-with-opacity rounded-r-[0.8rem] px-[0.5rem]`}
                      >
                        {commaNum(stockOrder.buyOrderCnt)}
                      </div>
                    </td>
                  ) : (
                    <td className="text-custom-black"></td>
                  )}
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </section>
  );
}
