import type { Meta, StoryObj } from '@storybook/react';

import Button from './Button';

const meta = {
  title: 'Common/Button',
  component: Button,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description:
        '버튼은 활성화될 때, 두 개 이상의 옵션 중 선택되지 않았을 때, 비활성화일 때의 모드로 나눌 수 있습니다.',
    },
    label: {
      description: '버튼의 텍스트입니다.',
    },
  },
} satisfies Meta<typeof Button>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Active: Story = {
  args: {
    mode: 'ACTIVE',
    label: '다음',
  },
};

export const NotSelected: Story = {
  args: {
    mode: 'NOT_SELECTED',
    label: '아니오',
  },
};

export const NonActive: Story = {
  args: {
    mode: 'NON_ACTIVE',
    label: '다음',
  },
};
