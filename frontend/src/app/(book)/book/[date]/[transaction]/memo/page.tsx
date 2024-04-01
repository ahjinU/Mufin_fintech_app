'use client';

import {
  Button,
  ComplexInput,
  FlexBox,
  GuideText,
  ImageUploadBox,
  Input,
  MoneyShow,
  OtherInfoElement,
  TinyButton,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import { useState } from 'react';

export default function BookListPost() {
  const [title, setTitle] = useState();
  const [amount, setAmount] = useState();
  const data = {
    storeName: '상점 A',
    transactionUUID: 'uuid1',
    type: '현금',
    amount: 50000,
    receptDetails: [
      {
        item: '상품 A',
        cnt: 2,
        price: 10000,
        total: 20000,
      },
      {
        item: '상품 B',
        cnt: 1,
        price: 20000,
        total: 20000,
      },
    ],
    content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
    category: '식료품',
  };
  return (
    <div className="p-[1.2rem] w-full flex flex-col gap-[26rem]">
      <div className="w-full flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          mode="LIST"
          topChildren={
            <OtherInfoElement
              leftExplainText={`${data.category}`}
              leftHighlightText={`${data.storeName}`}
              money={`${commaNum(data.amount)}원`}
              state={`${data.amount > 0 ? 'UP' : 'DOWN'}`}
            />
          }
        />
        <ComplexInput label={'메모를 남길 수 있어요!'} mode={'NONE'}>
          <textarea
            className="block w-full p-4 border rounded-[0.8rem] shadow-sm 
          focus:outline-none focus:ring-2 focus:custom-purple focus:border-transparent 
          resize-none text-customb-black custom-medium-text"
            rows={4}
            placeholder="내용을 입력해주세요..."
            value={data.content}
          />
        </ComplexInput>
      </div>
      <div>
        <Button mode={'ACTIVE'} label={'저장하기'} />
      </div>
    </div>
  );
}
