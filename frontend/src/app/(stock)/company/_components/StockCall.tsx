'use client';

import { MoneyShow } from '@/components';
import { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';

const name = '바람개비';

interface OrderData {
  price: number;
  buyOrderCnt: number;
  sellOrderCnt: number;
}

interface StockData {
  price: number;
  stockOrderList: OrderData[];
}

export function StockCall() {
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
        client.current?.send(`/pub/orders/${name}`, {});
        client.current?.subscribe(`/sub/orders/${name}`, (message: any) => {
          // 기존 봉 차트에 데이터 추가
          // setData((prev) => {
          //   return prev
          //     ? {...prev, JSON.parse(message.body)}
          //     : null;
          // });
          setData(JSON.parse(message.body));
        });
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
        money={['83', '43']}
        moneyColor={['text-custom-blue', 'text-custom-red']}
        text={['판매대기', '구매대기']}
        unit="주"
      />

      <table className="table table-fixed border-separate border-spacing-y-[0.5rem]">
        <thead className="custom-medium-text text-center">
          <tr>
            <th>판매 호가량</th>
            <th></th>
            <th>구매 호가량</th>
          </tr>
        </thead>
        <tbody className="custom-medium-text text-center">
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-2/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                10
              </div>
            </td>
            <td className="text-custom-red">291,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-1/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                1
              </div>
            </td>
            <td className="text-custom-black">278,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-3/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                32
              </div>
            </td>
            <td className="text-custom-black">264,500</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black flex justify-end">
              <div
                className={`w-2/5 bg-custom-blue-with-opacity rounded-l-[0.8rem]`}
              >
                10
              </div>
            </td>
            <td className="text-custom-black">263,000</td>
            <td className="text-custom-black"></td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">262,800</td>
            <td className="text-custom-black">
              <div
                className={`w-2/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                10
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">261,000</td>
            <td className="text-custom-black">
              <div
                className={`w-5/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                50
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-black">255,000</td>
            <td className="text-custom-black">
              <div
                className={`w-3/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                32
              </div>
            </td>
          </tr>
          <tr>
            <td className="text-custom-black"></td>
            <td className="text-custom-blue">244,000</td>
            <td className="text-custom-black">
              <div
                className={`w-2/5 bg-custom-red-with-opacity rounded-r-[0.8rem]`}
              >
                10
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  );
}
