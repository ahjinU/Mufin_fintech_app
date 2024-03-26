'use client';

import {
  Button,
  ComplexInput,
  FlexBox,
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
  return (
    <div className="p-[1.2rem] w-full flex flex-col justify-between h-full gap-[1rem]">
      <FlexBox
        isDivided={false}
        mode="LIST"
        topChildren={
          <OtherInfoElement
            leftExplainText={'화장품'}
            leftHighlightText={'씨제이올리브영 주식회사'}
            money={`${commaNum(3000)}원`}
            state={'UP'}
          />
        }
      />
      <ComplexInput label={'상세 사용 내역'} mode={'NONE'}>
        <ImageUploadBox text={'영수증 사진 업로드하기'} />
      </ComplexInput>
      <ComplexInput label={'기록을 남길 수 있어요!'} mode={'NONE'}>
        <div>
          <div className="flex justify-end mt-[-2.5rem]">
            <TinyButton label={'작성하기'} />
          </div>

          <textarea />
        </div>
      </ComplexInput>
    </div>
  );
}
