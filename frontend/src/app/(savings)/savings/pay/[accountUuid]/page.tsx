'use client';

import { Button, GuideText } from '@/components';
import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../../public/lotties/wallet.json';
import { useState, useEffect } from 'react';
import { AppliedSavingsListType } from '@/app/(savings)/_types';
import SavingsApis from '@/app/(savings)/_apis';
import { commaNum } from '@/utils/commaNum';
import { useRouter } from 'next/navigation';

function ContentRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <section className="flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </section>
  );
}

export default function PaySavings({
  params,
}: {
  params: { accountUuid: string };
}) {
  const router = useRouter();
  const accountUuid = params.accountUuid;
  const [appliedSavingsDetail, setAppliedSavingsDetail] =
    useState<AppliedSavingsListType>();
  const { getAppliedSavingsProductDetail } = SavingsApis();

  useEffect(() => {
    (async () => {
      const appliedSavingsDetailData = await getAppliedSavingsProductDetail(
        accountUuid,
      );
      setAppliedSavingsDetail(appliedSavingsDetailData?.data);
    })();
  }, []);

  return (
    <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] min-h-screen">
      <GuideText text="적금에 가입했을 때, 약속한 금액을 부모님에게 납부해요." />

      <div className="mx-auto mt-[-1.9rem]">
        <Lottie
          loop
          animationData={lottieJson}
          play
          style={{ width: 250, height: 250 }}
        />
      </div>

      <span className="custom-semibold-text">적금 상세 내역</span>
      <div className="bg-custom-light-gray rounded-[2rem] flex flex-col gap-[0.5rem] p-[1.2rem]">
        <ContentRow
          keyName="적금 상품 이름"
          value={appliedSavingsDetail?.savingsName || ''}
        />
        <ContentRow
          keyName="이자율"
          value={`${appliedSavingsDetail?.savingsInterest}%` || '0%'}
        />
        <ContentRow
          keyName="매달 납부 금액"
          value={`${commaNum(appliedSavingsDetail?.paymentAmount)}원` || '0원'}
        />
        <ContentRow
          keyName="매달 납부 일자"
          value={`${appliedSavingsDetail?.paymentDate}일` || '0일'}
        />
        <ContentRow
          keyName="적금 가입 일자"
          value={
            appliedSavingsDetail
              ? `${new Date(appliedSavingsDetail?.createdAt).getFullYear()}.${
                  new Date(appliedSavingsDetail?.createdAt).getMonth() + 1
                }.${new Date(appliedSavingsDetail?.createdAt).getDate()}`
              : '2024.1.1'
          }
        />
        <ContentRow
          keyName="적금 만기 일자"
          value={
            appliedSavingsDetail
              ? `${new Date(appliedSavingsDetail?.expiredAt).getFullYear()}.${
                  new Date(appliedSavingsDetail?.expiredAt).getMonth() + 1
                }.${new Date(appliedSavingsDetail?.expiredAt).getDate()}`
              : '2024.1.1'
          }
        />
        <ContentRow
          keyName="적금 연체 횟수"
          value={`${appliedSavingsDetail?.overdueCnt}회` || '0회'}
        />
      </div>

      <Button
        mode={
          appliedSavingsDetail?.savingsPeriod ===
          appliedSavingsDetail?.paymentCycle
            ? 'NON_ACTIVE'
            : 'ACTIVE'
        }
        label="다음"
        onClick={() => router.push(`/savings/pay/${accountUuid}/form`)}
      />
    </section>
  );
}
