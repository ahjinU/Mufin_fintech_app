'use client';
import {
  BackButton,
  Button,
  ComplexInput,
  GuideText,
  Header,
  Input,
  ProgressBar,
  Select,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import Link from 'next/link';
import { useEffect, useState } from 'react';
import useLoanApplyStore from '../_store';

export default function LoanApplyDetail() {
  const { updateApply } = useLoanApplyStore();

  const [isError, setIsError] = useState<boolean>(false);
  const [isActive, setIsActivie] = useState<boolean>(false);

  const [reason, setReason] = useState<string>('');
  const [amount, setAmount] = useState<number>();
  const [paymentTotalCnt, setPaymentTotalCnt] = useState<number>(1);
  const [paymentDate, setPaymentDate] = useState<number>(1);
  const [penalty, setPenalty] = useState<string>('');

  const [expectAmount, setExpectAmount] = useState<number>(0);

  useEffect(() => {
    if (amount != 0 && paymentTotalCnt === 0) {
      amount && setExpectAmount(amount);
    } else {
      if (!amount) return;
      const nextAmouont = amount / paymentTotalCnt;

      !isNaN(nextAmouont) &&
        isFinite(nextAmouont) &&
        setExpectAmount(Math.floor(nextAmouont));
    }
  }, [paymentTotalCnt, amount]);

  useEffect(() => {
    reason && reason.length > 15 ? setIsError(true) : setIsError(false);
  }, [reason]);

  useEffect(() => {
    reason !== '' && amount && penalty !== ''
      ? setIsActivie(true)
      : setIsActivie(false);
  }, [reason, penalty, amount]);

  const saveLoanApplyData = () => {
    if (!amount) return;
    updateApply({
      reason: reason,
      amount: amount,
      paymentTotalCnt: paymentTotalCnt,
      paymentDate: paymentDate,
      penalty: penalty,
    });
  };

  return (
    <>
      <Header>
        <BackButton label={'대출 신청하기'} />
      </Header>
      <div className="p-[1.2rem] gap-[0.5rem] flex flex-col w-full">
        <div className="flex flex-col gap-[1rem] mb-[1.5rem]">
          <GuideText
            text={
              <div>
                <span>
                  대출 받아야 하는 합리적인 금액을 설정해주세요.
                  <br /> 터무니 없는 금액은 반려될 수 있어요.
                </span>
              </div>
            }
          />
          <ProgressBar barGage={30} />
        </div>
        <ComplexInput
          label={'대출 목적이 무엇인가요?(최대 15자)'}
          mode={'ERROR'}
          isMsg={isError}
          message="15자를 넘길 수 없어요"
          height="h-[10rem]"
        >
          <Input value={reason} setValue={setReason} />
        </ComplexInput>
        <ComplexInput
          label={'원하는 대출 금액을 입력해주세요'}
          mode={'NONE'}
          height="h-[10rem]"
        >
          <div className="flex flex-row gap-[1rem] items-end">
            <Input
              value={amount}
              setValue={setAmount}
              width="w-[20rem]"
              reset={false}
            />
            <p className=" custom-semibold-text text-custom-light-purple">원</p>
          </div>
        </ComplexInput>
        <ComplexInput
          label={'대출 기한을 설정해주세요(최대 12개월)'}
          mode={'NONE'}
          height="h-[10rem]"
        >
          <div className="flex flex-row items-end">
            <Select mode={''} min={1} max={12} setValue={setPaymentTotalCnt} />
            <p className=" custom-semibold-text text-custom-light-purple">
              개월
            </p>
          </div>
        </ComplexInput>
        <ComplexInput
          label={'대출액 상환하는 날짜를 선택해주세요'}
          mode={'NONE'}
          height="h-[14rem]"
        >
          <div className="flex flex-col gap-[1rem] mt-[2rem]">
            <div className="flex flex-row custom-semibold-text text-custom-light-purple items-center gap-[0.7rem] mt-[-0.5rem]">
              <p>매월 </p>
              <Select
                mode={'LOAN'}
                min={1}
                max={12}
                setValue={setPaymentDate}
              />
            </div>
            <div className="flex flex-row custom-semibold-text text-custom-light-purple items-center gap-[0.7rem]">
              <p className="text-custom-dark-gray">{commaNum(expectAmount)}</p>
              <p>원씩 상환할 예정이에요.</p>
            </div>
          </div>
        </ComplexInput>
        <ComplexInput
          label={'패널티 규칙을 정해주세요(최대 20자)'}
          mode={'NONE'}
        >
          <div className="flex flex-col gap-[1rem]">
            <p className="leading-[1.2rem] text-custom-black custom-light-text">
              약속한 상환 날짜를 못지켰을 때,
              <br />
              무엇을 할지 정해보아요
            </p>
            <Input
              value={penalty}
              setValue={setPenalty}
              placeholder="설거지 10번 할게요"
            />
          </div>
        </ComplexInput>
      </div>
      {isActive ? (
        <Link
          className="fixed bottom-0 w-full p-[1.2rem] pt-0 bg-custom-white border-none outline-none"
          href={'/loan/apply/chat'}
          onClick={saveLoanApplyData}
        >
          <Button mode={isActive ? 'ACTIVE' : 'NON_ACTIVE'} label={'다음'} />
        </Link>
      ) : (
        <div className="fixed bottom-0 w-full p-[1.2rem] pt-0 bg-custom-white border-none outline-none">
          <Button mode={isActive ? 'ACTIVE' : 'NON_ACTIVE'} label={'다음'} />
        </div>
      )}
    </>
  );
}
