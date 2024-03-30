import {
  FlexBox,
  MoneyInfoElement,
  MoneyShow,
  OtherInfoElement,
} from '@/components';
import { useServerPostFetch } from '@/hooks/useServerFetch';
import { commaNum } from '@/utils/commaNum';
import Image from 'next/image';
import { LoanType } from '../_types';

export default async function LoanList() {
  const res = await useServerPostFetch({ api: '/loan/total/child' });
  const LoansList: LoanType[] = res?.data?.loansList;

  return (
    <div className="flex flex-col gap-[1rem]">
      <MoneyShow
        mode={'UNDIVIDED'}
        text={['남은 대출액']}
        money={[commaNum(30000)]}
        unit={'원'}
      />
      <div className="flex flex-row items-end justify-end gap-[1rem]">
        <div className="flex flex-row items-center gap-[0.3rem]">
          <Image
            src={'/images/icon-repay-smile.png'}
            width={200}
            height={200}
            alt={'정상 연체 중'}
            className="w-[2rem] h-[2rem]"
          />
          <p className="custom-light-text text-custom-dark-purple">
            정상 납부 중
          </p>
        </div>
        <div className="flex flex-row items-center gap-[0.3rem]">
          <Image
            src={'/images/icon-repay-sad.png'}
            width={200}
            height={200}
            alt={'연체 횟수 남음'}
            className="w-[2rem] h-[2rem]"
          />
          <p className="custom-light-text text-custom-red">연체 횟수 남음</p>
        </div>
      </div>
      {LoansList?.map((loan) => {
        return loan.loanRefusalReason ? (
          <FlexBox
            isDivided={true}
            topChildren={
              <div className="px-[1.2rem] my-[-1rem]">
                <OtherInfoElement
                  leftExplainText={`${commaNum(loan.amount)}원`}
                  leftHighlightText={loan.reason}
                  rightHighlightText="거절"
                  state={'UP'}
                />
              </div>
            }
            bottomChildren={
              <div className="flex flex-row gap-[1rem] px-[1.2rem] my-[-0.5rem] justify-between">
                <p className="text-custom-red">거절 사유</p>
                <p>{loan.loanRefusalReason}</p>
              </div>
            }
          />
        ) : loan.status == '심사중' ? (
          <FlexBox
            isDivided={false}
            topChildren={
              <MoneyInfoElement
                imageSrc={
                  loan.status === '연체중'
                    ? '/images/icon-repay-sad.png'
                    : '/images/icon-repay-smile.png'
                }
                leftHighlightText={`납부 ${loan.paymentNowCnt}개월/ 총 ${loan.paymentTotalCnt}개월`}
                leftExplainText={loan.reason}
                buttonOption={'TINY_BUTTON'}
                tinyButtonLabel={`납부하기`}
                link={`${loan.loanUuid}/detail`}
              />
            }
          />
        ) : (
          <FlexBox
            isDivided={false}
            topChildren={
              <div className="px-[1.2rem] my-[-1rem]">
                <OtherInfoElement
                  leftExplainText={`${commaNum(loan.amount)}원`}
                  leftHighlightText={loan.reason}
                  rightHighlightText="심사중"
                  state={'UP'}
                />
              </div>
            }
          />
        );
      })}
    </div>
  );
}
