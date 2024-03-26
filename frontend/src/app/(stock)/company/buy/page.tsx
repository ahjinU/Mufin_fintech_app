'use client';

import { useState, useEffect } from 'react';

import {
  GuideText,
  FlexBox,
  MoneyInfoElement,
  OtherInfoElement,
  Button,
} from '@/components';
import { StockBuySell } from '../_components/StockBuySell';
import { getChocoChipPocketData } from '../_apis';
import { commaNum } from '@/utils/commaNum';

export default function Buy() {
  const [price, setPrice] = useState<number>(0);
  const [quantity, setQuantity] = useState<number>(0);
  const totalPrice = price * quantity;
  const [chocoChipPocket, setChocoChipPocket] = useState<number>(0);

  useEffect(() => {
    (async function () {
      const data = await getChocoChipPocketData();
      setChocoChipPocket(data.data.balanceToday);
    })();
  }, []);

  return (
    <main className="p-[1.2rem] flex flex-col gap-[1rem]">
      <GuideText text="매수는 주식을 사는 것을 의미해요!" />

      <FlexBox
        isDivided={false}
        topChildren={
          <MoneyInfoElement
            imageSrc="/images/icon-dollar.png"
            leftHighlightText={`${commaNum(chocoChipPocket)} 초코칩`}
            leftExplainText="내 초코칩 보관함"
            buttonOption="RIGHT_ARROW"
          ></MoneyInfoElement>
        }
      />

      <FlexBox
        isDivided={false}
        topChildren={
          <OtherInfoElement
            imageSrc="/images/icon-dollar.png"
            leftHighlightText="바람막이 회사"
            leftExplainText="거래량 479주"
            rightHighlightText="871,030 초코칩"
            rightExplainText="+44.8%"
            state="UP"
          ></OtherInfoElement>
        }
      />

      <StockBuySell
        mode="BUY"
        handlePrice={setPrice}
        handleQuantity={setQuantity}
        totalPrice={totalPrice}
      />

      <Button mode="ACTIVE" label="확인" />
    </main>
  );
}
