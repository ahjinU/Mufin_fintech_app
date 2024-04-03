import { useState } from 'react';
import { RequestListType } from '../../_types/types';
import { Button, ComplexInput, GuideText, Input } from '@/components';
import { commaNum } from '@/utils/commaNum';

export default function AssesmentInfo({
  childInfo,
  buttonClick,
}: {
  childInfo: RequestListType;
  buttonClick: () => void;
}) {
  const { reason, amount, paymentTotalCnt, paymentDate } = childInfo;
  return (
    <div className="flex flex-col gap-[2rem]">
      <GuideText text="아이가 작성한 대출 리포트입니다. 꼼꼼하게 확인해주세요!" />
      <ComplexInput label="대출 목적" mode="NONE" height="h-fit">
        <Input value={reason} reset={false} disabled={true}></Input>
      </ComplexInput>
      <ComplexInput label="원하는 대출 금액" mode="NONE" height="h-fit">
        <div className="flex items-center gap-[0.8rem]">
          <Input
            value={commaNum(amount)}
            reset={false}
            disabled={true}
            isRight={true}
            width="w-3/5"
          ></Input>
          <p className="custom-semibold-text text-custom-black">원</p>
        </div>
      </ComplexInput>
      <ComplexInput
        label="원하는 대출 기한"
        mode="INFORM"
        height="h-fit"
        isMsg
        message={`매월 ${commaNum(
          Math.round(amount / paymentTotalCnt),
        )}원씩 상환될 예정이에요`}
      >
        <div className="flex items-center gap-[0.8rem]">
          <Input
            value={paymentTotalCnt}
            reset={false}
            disabled={true}
            isRight={true}
            width="w-1/5"
          ></Input>
          <p className="custom-semibold-text text-custom-black">개월</p>
        </div>
      </ComplexInput>
      <ComplexInput label="원하는 상환 날짜" mode="NONE" height="h-fit">
        <div className="flex items-center gap-[0.8rem]">
          <Input
            value={paymentDate}
            reset={false}
            disabled={true}
            isRight={true}
            width="w-1/5"
          ></Input>
          <p className="custom-semibold-text text-custom-black">일</p>
        </div>
      </ComplexInput>
      <div className="fixed w-full bottom-0 left-0 px-[1.2rem] py-[3rem]">
        <Button label="다음" mode="ACTIVE" onClick={buttonClick} />
      </div>
    </div>
  );
}
