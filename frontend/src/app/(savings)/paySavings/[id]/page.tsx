'use client';

import { Button, GuideText } from '@/components';
import { ContentRow } from '../../applySavings/page';
import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../public/lotties/wallet.json';
import { useEffect } from 'react';

export default function PaySavings({ params }: { params: { id: string } }) {
  const id = params.id;

  return (
    <section className="w-full p-[1.2rem] flex flex-col gap-[1rem]">
      <GuideText text="적금에 가입했을 때, 약속한 금액을 부모님에게 납부해요." />

      <div className="mx-auto">
        <Lottie
          loop
          animationData={lottieJson}
          play
          style={{ width: 250, height: 250 }}
        />
      </div>

      <span className="custom-semibold-text">적금 상세 내역</span>
      <div className="bg-custom-light-gray rounded-[2rem] flex flex-col gap-[0.5rem] p-[1.2rem]">
        <ContentRow keyName="적금 상품 이름" value="배고프면 적금을 들거라." />
        <ContentRow keyName="이자율" value="0.1%" />
        <ContentRow keyName="매달 납부 금액" value="5,000원" />
        <ContentRow keyName="매달 납부 일자" value="5일" />
        <ContentRow keyName="적금 가입 일자" value="2024.02.05" />
        <ContentRow keyName="적금 만기 일자" value="2025.02.04" />
        <ContentRow keyName="적금 미납 횟수" value="3회" />
      </div>

      <Button mode="ACTIVE" label="다음" onClick={() => console.log('hi')} />
    </section>
  );
}
