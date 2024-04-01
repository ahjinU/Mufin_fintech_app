import {
  Button,
  ComplexInput,
  FlexBox,
  GuideText,
  MoneyInfoElement,
  OtherInfoElement,
} from '@/components';
import { serverPostFetch } from '@/hooks/useServerFetch';
import Image from 'next/image';
import { LoanType } from '../../_types';
import { commaNum } from '@/utils/commaNum';
import Link from 'next/link';

export default async function RepayComplete() {
  const res = await serverPostFetch({ api: '/loan/total/child' });
  const LoansList: LoanType[] =
    res &&
    res?.data?.loansList.filter((loan: LoanType) => {
      return loan.status !== '심사중' && loan.status !== '거절';
    });
  return (
    <div className="flex flex-col">
      <GuideText text={`상환이 정상적으로 완료되었어요.`} />
      <div className="flex items-center justify-center mb-[-3rem]">
        <Image
          src={'/images/icon-loan-repay.png'}
          width={200}
          height={200}
          alt={'대출 반환'}
          className="w-[28rem] h-[28rem]"
        />
      </div>
      <ComplexInput label={'남은 대출 내역'} mode={'NONE'}>
        <div>
          {LoansList.length > 0 ? (
            LoansList?.map((loan, index) => {
              return (
                <FlexBox
                  key={`${index}-loan-list`}
                  isDivided={false}
                  topChildren={
                    <MoneyInfoElement
                      imageSrc={
                        loan.overDueCnt > 0
                          ? '/images/icon-repay-sad.png'
                          : '/images/icon-repay-smile.png'
                      }
                      leftHighlightText={`잔금 ${commaNum(
                        loan.remainderAmount,
                      )}원/ 총 ${commaNum(loan.amount)}원`}
                      leftExplainText={loan.reason}
                      buttonOption={'TINY_BUTTON'}
                      tinyButtonLabel={`납부하기`}
                      link={`${loan.loanUuid}/detail`}
                    />
                  }
                />
              );
            })
          ) : (
            <div className="custom-medium-text text-center mt-[2rem]">
              남은 대출이 없어요!
            </div>
          )}
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Link href={'/'}>
          <Button label="홈으로 이동" mode={'ACTIVE'} />
        </Link>
      </div>
    </div>
  );
}
