'use client';

import { Button, ComplexInput, Input, MoneyShow } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { usePathname } from 'next/navigation';
import { useState } from 'react';

export default function BookListPost() {
  const [title, setTitle] = useState();
  const [amount, setAmount] = useState();

  const path = usePathname();
  const postIndex = path?.indexOf('/post');
  const prePostPath = path?.slice(0, postIndex);
  const parts = prePostPath?.split('/');
  const lastPart = parts && parts[parts.length - 1];

  const savehandler = () => {
    const data = {
      date: lastPart,
      childUUID: 'hi',
      storeName: title,
      amount: amount,
    };
  };
  return (
    <div className="p-[1.2rem] w-full flex flex-col gap-[26rem]">
      <div className="w-full flex flex-col gap-[1rem]">
        <MoneyShow
          mode={'DIVIDED'}
          text={['지출', '수입']}
          money={[commaNum(-600), commaNum(5000)]}
          unit={'원'}
        />
        <ComplexInput label={'어디에서 또는 무엇에 사용했나요?'} mode={'ERROR'}>
          <Input value={title} setValue={setTitle} />
        </ComplexInput>
        <ComplexInput label={'얼마를 사용했나요?'} mode={'ERROR'}>
          <div className="flex flex-row items-end gap-[0.5rem]">
             value={amount} setValue={setAmount} width="5rem" />
            <p className="custom-semibold-text text-custom-black">원</p>
          </div>
        </ComplexInput>
      </div>
      <Button mode={'ACTIVE'} label={'추가하기'} onClick={savehandler} />
    </div>
  );
}
