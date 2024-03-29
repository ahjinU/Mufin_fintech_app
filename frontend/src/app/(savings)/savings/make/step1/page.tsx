'use client';

import { GuideText, Button, ComplexInput, Select, Input } from '@/components';
import Image from 'next/image';
import { useState } from 'react';

export default function MakeSavingsStep1() {
  // const [isBottomSheetOpen, setIsBottomSheetOpen] = useState<boolean>(false);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <GuideText text="상품 이름, 기간, 이자율을 정해주세요." />

        <ComplexInput label="상품 이름" mode="NONE">
          <Input />
        </ComplexInput>

        <ComplexInput
          label="적금 기간"
          mode="INFORM"
          isMsg={true}
          message="최대 12개월까지 지정할 수 있어요."
        >
          <Select min={1} max={12} mode="SAVINGS" />
        </ComplexInput>

        <ComplexInput
          label="이자율"
          mode="INFORM"
          isMsg={true}
          message="0.1%부터 10%까지 자유롭게 지정할 수 있어요."
        >
          <Input />
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button mode="ACTIVE" label="만들기" />
      </div>
    </>
  );
}
