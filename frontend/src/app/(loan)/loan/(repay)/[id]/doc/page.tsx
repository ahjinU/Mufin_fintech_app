'use client';

import { Button, FlexBox, GuideText } from '@/components';
import { usePathname } from 'next/navigation';
import LoanAPI from '../../../_api';
import { useEffect, useState } from 'react';
import { LoanDetailType } from '../../../_types';
import useLoanRepayStore from '../_store';
import { commaNum } from '@/utils/commaNum';
import { format } from 'date-fns';
import Link from 'next/link';
import useUserStore from '@/app/_store/store';

export default function LoanRepayDoc() {
  const { userData } = useUserStore();

  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[2];

  const { getLoanDetail } = LoanAPI();
  const { paymentCnt } = useLoanRepayStore();
  const [loan, setLoan] = useState<LoanDetailType>();

  useEffect(() => {
    (async function () {
      const res = id && (await getLoanDetail({ loanUuid: id }));
      setLoan(res?.data);
    })();
  }, [id]);

  return (
    <div className="flex flex-col gap-[1rem]">
      <GuideText text={`상환 금액을 확인하고, 상환을 진행해요.`} />
      <FlexBox
        isDivided={false}
        topChildren={
          <div className="flex flex-col gap-[3rem] justify-center items-center">
            <p className="custom-semibold-text">대출 상환서</p>
            <div className="w-[28rem]">
              <span className="custom-medium-text leading-[3.7rem]">
                <p>{`나(${userData.name})는 부모님께 빌린돈`}</p>
                <p>
                  {commaNum(loan?.totalAmount)}원 중{' '}
                  <span className="text-custom-purple">
                    {loan && commaNum(paymentCnt * loan?.paymentAmount)}
                  </span>
                  원 을 상환함으로써
                </p>
                <p>
                  현재까지 상환한 금액은{' '}
                  <span className="text-custom-light-purple">
                    {loan &&
                      commaNum(
                        loan?.totalAmount -
                          loan?.remainderAmount +
                          paymentCnt * loan?.paymentAmount,
                      )}
                  </span>
                  원이고,
                </p>
                <p>
                  앞으로 남은 금액은{' '}
                  <span className="text-custom-red">
                    {loan &&
                      commaNum(
                        loan?.remainderAmount -
                          paymentCnt * loan?.paymentAmount,
                      )}
                  </span>
                  원이라는 사실에 <br />
                  동의합니다.
                </p>
              </span>
            </div>
            <div className="w-[30rem] flex flex-col items-end justify-end custom-medium-text">
              {format(new Date(), 'yyyy-MM-dd')}
              <p>{userData?.name}</p>
            </div>
          </div>
        }
      />
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Link href={''}>
          <Button mode={'ACTIVE'} label={'동의하기'} />
        </Link>
      </div>
    </div>
  );
}
