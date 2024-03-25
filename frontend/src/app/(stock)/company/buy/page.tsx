'use client';

import { useState } from 'react';

import {
  GuideText,
  FlexBox,
  MoneyInfoElement,
  OtherInfoElement,
  Button,
} from '@/components';
import { StockBuySell } from '../_components/StockBuySell';

export default function Buy() {
  const [price, setPrice] = useState<number>(0);
  const [quantity, setQuantity] = useState<number>(0);
  const totalPrice = price * quantity;

  return (
    <main className="p-[1.2rem] flex flex-col gap-[1rem]">
      <GuideText text="매수는 주식을 사는 것을 의미해요!" />

      <FlexBox
        isDivided={false}
        topChildren={
          <MoneyInfoElement
            imageSrc="/images/icon-dollar.png"
            leftHighlightText="1,724,900 자스민"
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
