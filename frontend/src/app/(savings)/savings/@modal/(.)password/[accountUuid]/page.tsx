'use client';

import { useRouter, useSearchParams } from 'next/navigation';
import { PayPasswordBox } from '@/components';
import { useState, useEffect } from 'react';
import { AppliedSavingsListType } from '@/app/(savings)/_types';
import SavingsApis from '@/app/(savings)/_apis';
import useUserStore from '@/app/_store/store';

export default function Password({
  params,
}: {
  params: { accountUuid: string };
}) {
  const router = useRouter();
  const searchParams = useSearchParams();
  const period = searchParams?.get('period');

  const accountUuid = params.accountUuid;
  const [appliedSavingsDetail, setAppliedSavingsDetail] =
    useState<AppliedSavingsListType>();
  const { getAppliedSavingsProductDetail, paySavings } = SavingsApis();
  const { userData, setUserData } = useUserStore();

  useEffect(() => {
    (async () => {
      const appliedSavingsDetailData = await getAppliedSavingsProductDetail(
        accountUuid,
      );
      setAppliedSavingsDetail(appliedSavingsDetailData?.data);
      console.log(appliedSavingsDetailData?.data);
    })();
  }, []);

  return (
    <div
      className="absolute top-0 left-0 size-full bg-custom-black-with-opacity
    flex justify-center"
    >
      {appliedSavingsDetail && (
        <PayPasswordBox
          handleConfirmButton={async () => {
            if (period) {
              const result = await paySavings(
                appliedSavingsDetail.accountUuid,
                parseInt(period),
              );
              console.log(result);
              setUserData({
                ...userData,
                balance:
                  userData.balance -
                  appliedSavingsDetail.paymentAmount * parseInt(period),
              });
            }
            router.push('/result/savings/pay');
          }}
          handleCloseButton={() => router.back()}
          mode="CHECK"
          accountNumber={userData.accountNumber}
        />
      )}
    </div>
  );
}
