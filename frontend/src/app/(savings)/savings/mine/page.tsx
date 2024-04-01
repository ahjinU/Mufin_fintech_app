'use client';

import {
  BackButton,
  Header,
  FlexBox,
  MoneyInfoElement,
  TinyButton,
  MoneyShow,
  AlertConfirmModal,
} from '@/components';
import Image from 'next/image';
import { useState, useEffect } from 'react';
import { AppliedSavingsListType } from '../../_types';
import SavingsApis from '../../_apis';
import { commaNum } from '@/utils/commaNum';
import { useRouter } from 'next/navigation';
import useUserStore from '@/app/_store/store';

export default function MySavings() {
  const [appliedSavingsList, setAppliedSavingsList] = useState<
    AppliedSavingsListType[]
  >([]);
  const { getAppliedSavingsProduct, cancelSavings, terminateSavings } =
    SavingsApis();
  const totalPayment = appliedSavingsList.reduce(
    (prev, curr) => prev + curr.balance,
    0,
  );
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [targetAccountUuid, setTargetAccountUuid] = useState<string>('');
  const router = useRouter();
  const { userData, setUserData } = useUserStore();

  useEffect(() => {
    (async () => {
      const appliedSavingsListData = await getAppliedSavingsProduct();
      setAppliedSavingsList(appliedSavingsListData?.data?.savingsList);
    })();
  }, []);

  return (
    <>
      <Header>
        <BackButton label="나의 적금"></BackButton>
      </Header>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative">
        <MoneyShow
          mode="UNDIVIDED"
          text={['적금 누적 총액']}
          money={[`${commaNum(totalPayment)}`]}
          unit=" 원"
        />

        <div className="w-fit flex gap-[0.5rem] items-center custom-medium-text self-end">
          <Image
            src="/images/icon-smile.png"
            width={16}
            height={16}
            alt="정상 납부"
          />
          <span className="text-custom-blue">정상 납부 중</span>
          <Image
            src="/images/icon-sad.png"
            width={16}
            height={16}
            alt="정상 납부"
          />
          <span className="text-custom-red">연체 횟수 남음</span>
        </div>

        {appliedSavingsList &&
          appliedSavingsList.map((appliedSavings, index) => {
            return (
              <FlexBox
                key={`appliedSavings-${index}`}
                isDivided={true}
                topChildren={
                  <MoneyInfoElement
                    imageSrc={
                      appliedSavings.state === '정상' ||
                      appliedSavings.state === '만기'
                        ? '/images/icon-smile.png'
                        : '/images/icon-sad.png'
                    }
                    leftExplainText={
                      appliedSavings.savingsName.slice(0, 20) + ' ...'
                    }
                    leftHighlightText={`${commaNum(
                      appliedSavings.balance,
                    )}원 / ${commaNum(
                      appliedSavings.paymentAmount *
                        appliedSavings.savingsPeriod,
                    )}원`}
                    buttonOption="NO"
                  />
                }
                bottomChildren={
                  <div className="flex gap-[1rem] self-end">
                    <TinyButton
                      label={
                        appliedSavings.state === '만기'
                          ? '이자까지 전부 받기'
                          : '납부하기'
                      }
                      handleClick={async () => {
                        if (appliedSavings.state === '만기') {
                          const result = await terminateSavings(
                            appliedSavings.accountUuid,
                          ); // 아직 확인이 어려움
                          console.log(result);

                          router.push('/result/savings/success');
                        } else {
                          router.push(
                            `/savings/pay/${appliedSavings.accountUuid}`,
                          );
                        }
                      }}
                    />
                    {appliedSavings.state !== '만기' && (
                      <TinyButton
                        isWarning={true}
                        label="중도 해지하기"
                        handleClick={() => {
                          setTargetAccountUuid(appliedSavings.accountUuid);
                          setIsModalOpen(true);
                          setUserData({
                            ...userData,
                            balance: userData.balance + appliedSavings.balance,
                          });
                        }}
                      />
                    )}
                  </div>
                }
              />
            );
          })}
      </section>

      {isModalOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AlertConfirmModal
            text="적금 상품을 중도에 해지하시겠어요?"
            isOpen={isModalOpen}
            handleClickOkay={async () => {
              const result = await cancelSavings(targetAccountUuid);
              setIsModalOpen(false);
              router.refresh(); // 추후에 변경해야 함
            }}
            handleClickNo={() => setIsModalOpen(false)}
          />
        </div>
      )}
    </>
  );
}
