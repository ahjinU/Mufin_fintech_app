'use client';

import {
  GuideText,
  ComplexInput,
  Select,
  Input,
  Button,
  AlertConfirmModal,
} from '@/components';
import { useState } from 'react';
import { useRouter, useParams } from 'next/navigation';
import SavingsApis from '../../../../_apis/index';

export default function ApplySavingsStep2() {
  const [date, setDate] = useState<number>(1);
  const [money, setMoney] = useState<string>('');
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const { applySavingsProduct } = SavingsApis();
  const router = useRouter();
  const params = useParams<{ savingsId: string }>();

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <GuideText text="정기적으로 매월 납부할 날짜와 금액을 설정해주세요." />

        <ComplexInput mode="NONE" label="적금 납부 일자">
          <Select mode="LOAN" min={1} max={30} setValue={setDate} />
        </ComplexInput>

        <ComplexInput mode="NONE" label="납부할 금액">
          <div className="flex gap-[1rem] items-center">
            <Input
              placeholder="1,000"
              value={money}
              setValue={setMoney}
              type="tel"
            />
            <span className="custom-semibold-text text-custom-light-purple">
              원
            </span>
          </div>
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button
          mode="ACTIVE"
          label="완료"
          onClick={() => setIsModalOpen(true)}
        />
      </div>

      {isModalOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AlertConfirmModal
            text="적금 상품을 가입하시겠어요?"
            isOpen={isModalOpen}
            handleClickOkay={async () => {
              if (params) {
                const result = await applySavingsProduct(
                  params.savingsId,
                  parseInt(money),
                  date,
                );
                console.log(result);
                router.replace('/result/savings/apply');
              }
            }}
            handleClickNo={() => setIsModalOpen(false)}
          />
        </div>
      )}
    </>
  );
}
