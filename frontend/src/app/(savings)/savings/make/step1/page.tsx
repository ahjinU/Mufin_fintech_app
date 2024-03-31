'use client';

import {
  GuideText,
  Button,
  ComplexInput,
  Select,
  Input,
  AlertConfirmModal,
} from '@/components';
import { useState } from 'react';
import SavingsApis from '@/app/(savings)/_apis';
import { useRouter } from 'next/navigation';

export default function MakeSavingsStep1() {
  const [name, setName] = useState<string>('');
  const [month, setMonth] = useState<number>(1);
  const [rate, setRate] = useState<string>('');
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const { registerSavingsProduct } = SavingsApis();
  const router = useRouter();

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <GuideText text="상품 이름, 기간, 이자율을 정해주세요." />

        <ComplexInput label="상품 이름" mode="NONE">
          <Input
            placeholder="이해하기 편한 이름으로 정해주세요."
            value={name}
            setValue={setName}
          />
        </ComplexInput>

        <ComplexInput
          label="적금 기간"
          mode="INFORM"
          isMsg={true}
          message="최대 12개월까지 지정할 수 있어요."
        >
          <Select min={1} max={12} mode="SAVINGS" setValue={setMonth} />
        </ComplexInput>

        <ComplexInput
          label="이자율"
          mode="INFORM"
          isMsg={true}
          message="0.1%부터 10%까지 자유롭게 지정할 수 있어요."
        >
          <Input placeholder="0.1" value={rate} setValue={setRate} />
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button
          mode="ACTIVE"
          label="만들기"
          onClick={() => setIsModalOpen(true)}
        />
      </div>

      {isModalOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AlertConfirmModal
            text="적금 상품을 만드시겠어요?"
            isOpen={isModalOpen}
            handleClickOkay={async () => {
              await registerSavingsProduct(name, month, parseFloat(rate));
              router.replace('/result/savings/make');
            }}
            handleClickNo={() => setIsModalOpen(false)}
          />
        </div>
      )}
    </>
  );
}
