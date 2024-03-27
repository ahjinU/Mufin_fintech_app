'use client';

interface CompanyDataType {
  price: number;
  incomeRatio: number;
  transCnt: number;
  imageUrl: string | null;
}

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import {
  FlexBox,
  MoneyInfoElement,
  OtherInfoElement,
  Button,
  GuideText,
} from '@/components';
import { StockBuySell } from '../_components/StockBuySell';
import { getChocoChipPocketData, getCompanyData, sellStock } from '../_apis';
import { commaNum } from '@/utils/commaNum';

export default function Sell() {
  const [price, setPrice] = useState<number>(0);
  const [quantity, setQuantity] = useState<number>(0);
  const totalPrice = price * quantity;

  const [chocoChipPocket, setChocoChipPocket] = useState<number>(0);
  const [company, setCompany] = useState<CompanyDataType>({
    price: 0,
    incomeRatio: 0,
    transCnt: 0,
    imageUrl: null,
  });

  const router = useRouter();

  useEffect(() => {
    (async function () {
      // const data = await getChocoChipPocketData();
      // setChocoChipPocket(data.data.balanceToday);
    })();
  }, []);

  useEffect(() => {
    (async function () {
      // const data = await getCompanyData('바람막이');
      // setCompany(data.data);
    })();
  }, []);

  return (
    <main className="px-[1.2rem] flex flex-col gap-[1rem]">
      <GuideText text="매도는 주식을 파는 것을 의미해요!" />

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
            imageSrc={company.imageUrl}
            leftHighlightText="바람막이 회사"
            leftExplainText={`거래량 ${commaNum(company.transCnt)}주`}
            rightHighlightText={`${commaNum(company.price)} 초코칩`}
            rightExplainText={`${company.incomeRatio}%`}
            state={company.incomeRatio >= 0 ? 'UP' : 'DOWN'}
          ></OtherInfoElement>
        }
      />

      <StockBuySell
        mode="SELL"
        handlePrice={setPrice}
        handleQuantity={setQuantity}
        totalPrice={totalPrice}
      />

      <Button
        mode="ACTIVE"
        label="확인"
        onClick={async () => {
          // await sellStock('바람막이', price, quantity);
          router.push('/stock/storage');
        }}
      />
    </main>
  );
}
