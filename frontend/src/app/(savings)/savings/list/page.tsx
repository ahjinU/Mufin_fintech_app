'use client';

import { useState, useEffect } from 'react';
import {
  BackButton,
  Header,
  FlexBox,
  MoneyInfoElement,
  PlusButton,
  AlertConfirmModal,
} from '@/components';
import SavingsApis from '../../_apis';
import { SavingsListType } from '../../_types';
import { useRouter } from 'next/navigation';

export default function SavingsList() {
  const { getSavingsProductList, removeSavingsProduct } = SavingsApis();
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [targetSavingsUuid, setTargetSavingsUuid] = useState<string>('');
  const [savingsList, setSavingsList] = useState<SavingsListType[]>([]);
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      const savingsListData = await getSavingsProductList();
      setSavingsList(savingsListData?.data?.savingsList);
    };

    fetchData();
  }, []);

  return (
    <>
      <section>
        <Header>
          <BackButton label="등록된 적금 상품" />
        </Header>

        <section className="w-full p-[1.2rem] flex flex-col gap-[1rem]">
          {savingsList?.map((savings, index) => {
            return (
              <FlexBox
                key={`적금상품-${index}`}
                isDivided={false}
                topChildren={
                  <MoneyInfoElement
                    leftHighlightText={savings.name.slice(0, 15) + ' ...'}
                    leftExplainText={`${new Date(
                      savings.createdAt,
                    ).getFullYear()}년 ${
                      new Date(savings.createdAt).getMonth() + 1
                    }월 ${new Date(savings.createdAt).getDate()}일`}
                    buttonOption="TINY_BUTTON"
                    tinyButtonLabel="삭제하기"
                    handleTinyButton={() => {
                      setIsModalOpen(true);
                      setTargetSavingsUuid(savings.savingsUuid);
                    }}
                  />
                }
              />
            );
          })}

          <PlusButton
            label="적금 상품 더 만들기"
            handleClick={() => router.push('/savings/make')}
          />
        </section>
      </section>
      {isModalOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AlertConfirmModal
            text="적금 상품을 삭제하시겠어요?"
            isOpen={isModalOpen}
            handleClickOkay={async () => {
              await removeSavingsProduct(targetSavingsUuid);
              setIsModalOpen(false);
              router.refresh();
            }}
            handleClickNo={() => setIsModalOpen(false)}
          />
        </div>
      )}
    </>
  );
}
