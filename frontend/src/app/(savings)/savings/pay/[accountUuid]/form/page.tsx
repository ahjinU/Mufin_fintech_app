'use client';

import { AccountBox, ComplexInput, Select, Button } from '@/components';
import { useRouter } from 'next/navigation';
import { useState, useEffect } from 'react';
import { AppliedSavingsListType } from '@/app/(savings)/_types';
import SavingsApis from '@/app/(savings)/_apis';
import { commaNum } from '@/utils/commaNum';

export default function PaySavingsForm({
  params,
}: {
  params: { accountUuid: string };
}) {
  const router = useRouter();
  const accountUuid = params.accountUuid;
  const [month, setMonth] = useState<number>(1);
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
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem] relative">
        <AccountBox
          text="내 계좌에서 돈이 빠져나가요!"
          isGrayBackground={true}
          money={appliedSavingsDetail?.balance || 0}
        />

        <ComplexInput mode="NONE" label="납부가 완료된 개월 수">
          <span className="custom-medium-text">
            <span className="text-custom-purple">
              {appliedSavingsDetail?.paymentCycle}
            </span>
            개월 / 6개월
          </span>
        </ComplexInput>

        <ComplexInput mode="NONE" label="약속한 1개월 납부 금액">
          <span className="custom-medium-text">
            {commaNum(appliedSavingsDetail?.paymentAmount)}원
          </span>
        </ComplexInput>

        <ComplexInput
          mode="INFORM"
          label="몇 개월치를 납부할까요?"
          isMsg={true}
          message={`오늘은 ${month}개월치 ${commaNum(
            (appliedSavingsDetail?.paymentAmount || 0) * month,
          )}원 납부할게요!`}
        >
          <Select
            mode="SAVINGS_RETURN"
            min={1}
            max={appliedSavingsDetail?.overdueCnt || 1}
            setValue={setMonth}
          />
        </ComplexInput>
      </section>

      <div className="absolute w-full bottom-0 left-0 p-[1.2rem]">
        <Button
          mode="ACTIVE"
          label="납부하기"
          onClick={() =>
            router.push(
              `/savings/password/${appliedSavingsDetail?.accountUuid}?period=${month}`,
            )
          }
        />
      </div>
    </>
  );
}
